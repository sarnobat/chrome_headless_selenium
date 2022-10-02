JAVA_HOME17=/Volumes/numerous/usr/local/homebrew/Cellar/openjdk@17/17.0.4.1_1/
# CHROMEDRIVER=/Volumes/git/github/chrome_headless_selenium/1_selenium/chromedriver_mac_m1
CHROMEDRIVER=/Volumes/git/github/chrome_headless/1_selenium/java/chromedriver.mac64.intel.2022-07
echo "www.bbc.co.uk" | JAVA_HOME=$JAVA_HOME17 $JAVA_HOME17/bin/java -classpath $HOME/github/groovy_libraries/.groovy/lib/guava-19.0.jar:selenium-java-4.5.0.jar:gson-2.7.jar:$HOME/github/groovy_libraries/.groovy/lib/json-20140107.jar:selenium-remote-driver-2.53.1.jar:httpclient-4.3.3.jar:httpcore-4.4.5.jar:commons-exec-1.3.jar:commons-logging-1.2.jar Headless.java $CHROMEDRIVER  | tee out.html
