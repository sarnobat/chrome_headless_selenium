# pip3 install selenium
# pip3 install webdriver-manager
# /Volumes/numerous/usr/local/homebrew/lib/python3.9/site-packages

from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
import sys

driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))
print(sys.argv[1])
# driver.get("https://www.google.com")
driver.get(str(sys.argv[1]))
print(driver.title)
driver.close()

