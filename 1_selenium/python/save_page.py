# pip3 install selenium
# pip3 install webdriver-manager
# /Volumes/numerous/usr/local/homebrew/lib/python3.9/site-packages

from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager

import time
import pyautogui

driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))
driver.get("https://www.google.com")
print(driver.title)

time.sleep(3)
pyautogui.hotkey('command', 's')
time.sleep(10)
pyautogui.typewrite('/tmp/out.html')
# pyautogui.hotkey('enter')
# 
# 
# driver.close()