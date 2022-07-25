# pip3 install selenium
# pip3 install webdriver-manager

# /Volumes/numerous/usr/local/homebrew/lib/python3.9/site-packages

# from selenium import webdriver
# 
# driver = webdriver.Chrome(executable_path="1_selenium/chromedriver.mac64.intel.2022-07")
# driver.get("http://www.google.com")
# print(driver.title)
# driver.close()

from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager

driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))
driver.get("https://www.google.com")
print(driver.title)
driver.close()