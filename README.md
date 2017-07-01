# coddy

Build and run back:
```bash
# Move into back directory
cd /home/back
# Clean up
rm -rf target
# Build back
mvn package
# Launch WAR
java -jar target/coddy-back-0.1.0-SNAPSHOT.war
```

Build and run front:
```bash
# Move into front directory
cd /home/front/src
# Launch front (watch mode)
npm start
```