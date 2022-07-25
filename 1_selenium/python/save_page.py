# pip3 install selenium
# pip3 install webdriver-manager
# /Volumes/numerous/usr/local/homebrew/lib/python3.9/site-packages

from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager

import time
import pyautogui

chrome_options = webdriver.chrome.options.Options()
# prefs = {"download.default_directory": "/tmp/" , # pass the variable
#                    "download.prompt_for_download": True,
#                    "directory_upgrade": True,
#                    "safebrowsing.enabled": True }
# chrome_options.add_experimental_option('prefs', prefs)




driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()),options=chrome_options)
driver.get("https://www.google.com")


time.sleep(1)
# pyautogui.hotkey('command', 's')
pyautogui.keyDown('command')
pyautogui.press('s')
pyautogui.keyUp('command')
time.sleep(1)
# pyautogui.typewrite('helloworld')
pyautogui.typewrite('/')
# pyautogui.press('enter')
print(driver.title)
pyautogui.typewrite('/tmp/')
pyautogui.hotkey('enter')
# s/pyautogui.press('/')
# pyautogui.typewrite('/tmp/')
# pyautogui.hotkey('enter')
# pyautogui.typewrite('/tmp/out.html')
# pyautogui.hotkey('enter')
# 
# 
# driver.close()
time.sleep(20)