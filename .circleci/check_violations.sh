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

if [ -z "$l_violation_nb_accepted" ]; then
    echo "the number of violation which can be accepted is empty. You must set this value"
    exit 1
fi

# Check if jq is present, if not install it
if command -v jq > /dev/null; then
    echo "jq is present"
else
    sudo apt-get install -y jq
fi

result=$(curl -s -XGET "${l_sonar_url}$l_sonar_api?component=${l_sonar_project_key}&metricKeys=${l_violation_type}_violations" | jq '.component.measures[].value')

# remove quote as a suffix in result
result="${result%\"}"
# remove quote as a prefix in result
result="${result#\"}"

if  [[ ${result} -gt ${l_violation_nb_accepted} ]]; then
    echo "the number of issue with the criticality ${l_violation_type} is greater than ${l_violation_nb_accepted} which is the number accepted"
fi