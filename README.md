# Custom Capability Matcher
Example that shows how to implement a Selenium Grid capability matcher updated to latest version of Selenium Server (old implementation of a tutorial that can be
found [here](https://rationaleemotions.wordpress.com/2014/01/19/working-with-a-custom-capability-matcher-in-the-grid/)).
With this matcher you can use the hub for Appium Node and choose a node based on deviceName capability.

## How to generate the jar
_It will be placed in the target folder_
```
    $ mvn -DskipTests=true package
```

## Steps to start the grid
1. Download Selenium Server 3.13

  ```
    $ wget https://goo.gl/4g538W
  ```
1. Start the hub with a the specific configuration

  ```
    $ java -cp selenium-server-standalone-3.13.0.jar;custom-capability-matcher-1.0-SNAPSHOT.jar; org.openqa.grid.selenium.GridLauncherV3 -capabilityMatcher com.example.selenium.matcher.MobileCapabilityMatcher -role hub -debug -hubConfig hubConfig.json
  ```
1. Start the `foo` node for S6 mobile phone

  ```
    $ java -jar selenium-server-standalone-2.53.0.jar -role node -hub http://localhost:4444/grid/register -nodeConfig src/main/resources/nodeConfig_foo.json
  ```
1. Start the `bar` node for Nexus phone

  ```
    $ java -cp selenium-server-standalone-3.13.0.jar;custom-capability-matcher-1.0-SNAPSHOT.jar; org.openqa.grid.selenium.GridLauncherV3  -role node -hub http://localhost:4444/grid/register -debug -nodeConfig nodeConfig_foo.json
  ```
  
## Run the test 
```
  $ mvn test
```
Change this [line](https://github.com/diemol/custom-capability-matcher/blob/master/src/test/java/SampleCapabilityMatcherTest.java#L17) to see the matcher in action by:
* Either setting `deviceName` capability to `S6` or `NEXUS` and see the test getting executed in the desired node.
* Or setting `nodeName` to a different value and seeing the Grid reject the request because no node matches the capabilities.


