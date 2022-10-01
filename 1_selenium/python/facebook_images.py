# pip3 install selenium
# pip3 install webdriver-manager

import sys

# 3.8.2
from selenium import webdriver
from selenium.webdriver import Keys, ActionChains
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager

import time
import pyautogui




chrome_options = webdriver.chrome.options.Options()
chrome_options.add_argument("--disable-notifications");
driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()),options=chrome_options)

url="https://www.facebook.com/"
driver.get(url)
time.sleep(3)

ActionChains(driver).send_keys_to_element(driver.find_elements("xpath", '//input[@id="email"]')[0], "ss533@cornell.edu").perform()
ActionChains(driver).send_keys_to_element(driver.find_elements("xpath", '//input[@id="pass"]')[0], "aize2FEN!").perform()
driver.find_elements("xpath", '//button[@type="submit"]')[0].click()
time.sleep(10)
driver.get("https://www.facebook.com/megha.panchamukhi.7/photos_by")
time.sleep(3)
