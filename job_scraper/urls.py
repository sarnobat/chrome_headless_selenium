# pip3 install selenium
# pip3 install webdriver-manager
# /Volumes/numerous/usr/local/homebrew/lib/python3.9/site-packages

from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
import sys
import time

# https://chromedriver.storage.googleapis.com/114.0.5735.90/chromedriver_mac64.zip
# /Volumes/trash/trash/chromedriver.mac64.intel.114.0.5735.90
# driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))
driver = webdriver.Chrome(executable_path=r"/Volumes/trash/trash/chromedriver.mac64.intel.114.0.5735.90")
driver.set_window_size(1200, 600)
# print(sys.argv[1] or "https://www.google.com/")
# driver.get("https://www.google.com")


try:
	url = sys.argv[1] if len(sys.argv) > 1 else "https://www.google.com/"
	driver.get(url)
	time.sleep(10)
	print(driver.title)
	href_urls = []
	for link in driver.find_elements_by_tag_name("a"):
		href = link.get_attribute("href")
		if href != None:
			href_urls.append(href)

	xs = [x for x in href_urls if x is not None]
	# for element in href_urls if element is not None:
	for element in sorted(xs):
		print(element)
		
# 	driver.close()

finally:
	driver.close()
