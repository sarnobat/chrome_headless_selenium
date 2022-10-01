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

def printLinksInCurrentPage(url):
	driver.get(url)
	time.sleep(4)

# 	printLinksInCurrentPage(url)
	elems = driver.find_elements("xpath", '//a[@href]')
	for elem in elems:
		print(elem.get_attribute("href"))

	print("----------------------------------------- ")
	
# 	elems = driver.find_elements("xpath", '//*/a[@rel="next"]')
# 	for elem in elems:
# 		print(elem.get_attribute("href"))
# 		printLinksInCurrentPage(elem.get_attribute("href"))
# 	print(elems[0].get_attribute("href"))
	time.sleep(5)


url = None
if sys.argv[1:]:
	#url=sys.argv[1]
	password=sys.argv[1]
if url == None:
	url="https://www.facebook.com/"

chrome_options = webdriver.chrome.options.Options()
driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()),options=chrome_options)

# printLinksInCurrentPage(url)

driver.get(url)
time.sleep(3)

# elems = 
# bet_fa = driver.find_element_by_id("email")
# bet_fa.send_keys("0.00000005")
# driver.find_elements("xpath", '//input[@id="email"]')[0]

ActionChains(driver).send_keys_to_element(driver.find_elements("xpath", '//input[@id="email"]')[0], "ss533@cornell.edu").perform()
ActionChains(driver).send_keys_to_element(driver.find_elements("xpath", '//input[@id="pass"]')[0], "aize2FEN!").perform()
driver.find_elements("xpath", '//button[@type="submit"]')[0].click()
time.sleep(30)
driver.get("https://www.facebook.com/megha.panchamukhi.7/photos_by")
time.sleep(3)

# print(driver.find_elements("xpath", '//input[@id="email"]')[0])
# .sendKeys("your value")
# for elem in elems:
# 	print(elem.get_attribute("href"))

# print("TODO: get subsequent pages")
# time.sleep(15)

# time.sleep(15)