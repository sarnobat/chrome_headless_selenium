# pip3 install selenium
# pip3 install webdriver-manager

import sys

from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager

import time
import pyautogui

def printLinksInCurrentPage(url):
	driver.get(url)
	time.sleep(4)

# 	printLinksInCurrentPage(url)
	elems = driver.find_elements("xpath", '//a[@href]')
	for elem in elems:
		print(elem.get_attribute("href"))

	print("----------------------------------------- ")
	elems = driver.find_elements("xpath", '//*[@id="yui-gen3"]/span[5]/a')
# 	time.sleep(15)
	printLinksInCurrentPage(elems[0].get_attribute("href"))


url = None
if sys.argv[1:]:
	url=sys.argv[1]
if url == None:
	url="https://forums.liverpoolfc.com/search.php?do=finduser&userid=134992&starteronly=1&contenttype=vBForum_Thread"

chrome_options = webdriver.chrome.options.Options()
driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()),options=chrome_options)

printLinksInCurrentPage(url)

# print("TODO: get subsequent pages")
# time.sleep(15)

time.sleep(15)