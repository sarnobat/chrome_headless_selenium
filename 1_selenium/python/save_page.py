# pip3 install selenium
# pip3 install webdriver-manager

import sys

from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager

import time
import pyautogui

url = None
if sys.argv[1:]:
	url=sys.argv[1]
if url == None:
	url="http://www.google.com"

chrome_options = webdriver.chrome.options.Options()
driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()),options=chrome_options)
driver.get(url)
time.sleep(1)

pyautogui.keyDown('command')
pyautogui.press('s')
pyautogui.keyUp('command')
time.sleep(1)

pyautogui.typewrite('/')
# print(driver.title)
time.sleep(1)
pyautogui.typewrite('/tmp/')
time.sleep(1)
pyautogui.hotkey('enter')
time.sleep(1)
pyautogui.hotkey('enter')
time.sleep(60)