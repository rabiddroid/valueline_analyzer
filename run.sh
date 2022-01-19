mvn clean package
mvn exec:java -Dexec.mainClass="com.cosmicapps.valueline.App" -Dexec.args="/Users/jeffrey/Dev/valueline_analyzer/src/test/resources/visa_VL.jpg" > output.txt