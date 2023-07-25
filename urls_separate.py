# pip3 install selenium
# pip3 install webdriver-manager
# /Volumes/numerous/usr/local/homebrew/lib/python3.9/site-packages

from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.common.by import By

import sys
import time

driver = webdriver.Chrome(executable_path=r"/Volumes/trash/trash/chromedriver.mac64.intel.114.0.5735.90")
print(sys.argv[1] or "https://www.google.com/")

driver.get(str(sys.argv[1]) or "https://www.google.com/")
driver.get(str(sys.argv[2]) or "job-tile__title")
print(driver.title)

time.sleep(10)

href_urls = []
for link in driver.find_elements_by_tag_name("a"):
	href = link.get_attribute("href")
	if href != None:
		# TODO: find child element with class job-tile__title
		children = link.find_elements(By.CLASS_NAME, 'job-tile__title')
		desc = "unknown"
		for child in children:
			desc = child.get_attribute('innerHTML')
		href_urls.append('{message: <32}'.format(message=desc) + "\t" + href)

xs = [x for x in href_urls if x is not None]

for element in sorted(xs):
	print(element)
        
driver.close()

