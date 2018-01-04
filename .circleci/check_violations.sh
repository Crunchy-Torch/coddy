#!/bin/bash

l_sonar_url="https://sonarcloud.io"
l_sonar_project_key="coddy-back"

l_sonar_api="/api/measures/component"
l_violation_type=$1
l_violation_nb_accepted=$2

if [ -z "$l_violation_type" ]; then
    echo "the type of violation to check is empty. You must set this value"
    exit 1
fi

# Check if jq is present, if not install it
if command -v jq > /dev/null; then
    echo "jq is present"
else
    sudo apt-get install -y jq
fi

l_branch=${CIRCLE_BRANCH}

if [ -z "${l_branch}" ]; then
    l_branch="master"
fi

# wait a moment that the result will be available in sonar
l_loop=0
l_endLoop=4
l_wait=1

curl -s -XGET "${l_sonar_url}$l_sonar_api?component=${l_sonar_project_key}&metricKeys=${l_violation_type}_violations&branch=${l_branch}" | jq '.component.measures[].value' > /dev/null
response=$?

while [ ${response} != 0 ] && [ ${l_loop} != ${l_endLoop} ]; do
    sleep ${l_wait}
    curl -s -XGET "${l_sonar_url}$l_sonar_api?component=${l_sonar_project_key}&metricKeys=${l_violation_type}_violations&branch=${l_branch}" | jq '.component.measures[].value' > /dev/null
    response=$?
    l_loop=$(( $l_loop + 1))
    l_wait=$(( $l_wait*2))
done

if [ ${response} != 0 ]; then
    echo "no branch with the name ${l_branch} is available on sonarqube"
    exit 1;
else
    echo "a branch with the name ${l_branch} is available, starting to check the result..."
fi

# Check the result
result=$(curl -s -XGET "${l_sonar_url}$l_sonar_api?component=${l_sonar_project_key}&metricKeys=${l_violation_type}_violations&branch=${l_branch}" | jq '.component.measures[].value')

# remove quote as a suffix in result
result="${result%\"}"
# remove quote as a prefix in result
result="${result#\"}"

if [[ "${l_branch}" == "master" ]]; then

    if [ -z "$l_violation_nb_accepted" ]; then
        echo "the number of violation which can be accepted is empty. You must set this value"
        exit 1
    fi

    if  [[ ${result} -gt ${l_violation_nb_accepted} ]]; then
        echo "the number of issue with the criticality ${l_violation_type} is greater than ${l_violation_nb_accepted} which is the number accepted"
        exit 1
    fi

else

    if  [[ ${result} -gt 0 ]]; then
        echo "You have ${result} new issue with the criticality ${l_violation_type}, please resolve them before make a pull request"
        exit 1
    else
        echo "you don't have any new issue with the criticality ${l_violation_type}, which is great !!
Good luck with your future pull request, we hope it will be accepted ASAP.
And of course thank you for your help!! :)"
    fi
fi