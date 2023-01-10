# pip3 install selenium
# pip3 install webdriver-manager

import sys

from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager

import time
import pyautogui

# def printLinksInCurrentPage(url):


url = None
if sys.argv[1:]:
	url=sys.argv[1]
if url == None:
	url="https://www.tiktok.com/@totallove/video/7131444669090712838?is_from_webapp=v1&item_id=7131444669090712838"

chrome_options = webdriver.chrome.options.Options()
driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()),options=chrome_options)

# printLinksInCurrentPage(url)
# looking for this
# https://v16m-webapp.tiktokcdn-us.com/f68028fd106bdee6c04b20c492fb5b7c/63146ee2/video/tos/useast2a/tos-useast2a-ve-0068c002/8607da1e99394e70b18ca1c8550e3230/?a=1988&ch=0&cr=0&dr=0&lr=tiktok_m&cd=0%7C0%7C0%7C0&br=3518&bt=1759&cs=0&ds=3&ft=ebtHKH-qMyq8ZyjK~he2NIOufl7Gb&mime_type=video_mp4&qs=0&rc=PDs8OWY7NjQ8NWY3PDpkOEBpamxtPDM6Zmd5ZTMzNzczM0AuYmAwYjJfNWMxXjYtLy01YSMycWpwcjRnZm9gLS1kMTZzcw%3D%3D&l=20220904032428AEAF19328B7C571ED625
# print(driver.find_elements("xpath","/html/body").text)

driver.get(url)
time.sleep(4)

# 	printLinksInCurrentPage(url)
elems = driver.find_elements("xpath", '//*/video[@src]')
for webElement in elems:
	print(webElement.get_attribute("src"))
# 		print(webElement)
# 	attrs=[]
# 	for attr in webElement.get_property('attributes'):
# 		attrs.append([attr['name'], attr['value']])
# 	print(attrs)
# 		print(webElement.get_name())
# 		print(webElement.get_attribute("href"))

# print(driver.page_source)

print("----------------------------------------- ")
	
# 	elems = driver.find_elements("xpath", '//*/a[@rel="next"]')
# 	for elem in elems:
# 		print(elem.get_attribute("href"))
# 		printLinksInCurrentPage(elem.get_attribute("href"))
# 	print(elems[0].get_attribute("href"))
# 	time.sleep(5)


driver.close()

# print("TODO: get subsequent pages")
# time.sleep(5)

# time.sleep(15)