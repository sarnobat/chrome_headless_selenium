# pip3 install selenium
# pip3 install webdriver-manager
# /Volumes/numerous/usr/local/homebrew/lib/python3.9/site-packages


from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.common.by import By
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.support.ui import WebDriverWait

from selenium.webdriver.support import expected_conditions as EC


import sys
import time


try:

	url = 'https://paypal.eightfold.ai/careers?query=Java&location=San%20Jose&pid=274894578339' if len(sys.argv) < 2 else sys.argv[1]

# 	url = {True: sys.argv[1], False: 'https://paypal.eightfold.ai/careers?query=Java&location=San%20Jose&pid=274894578339'} [len(sys.argv) > 1]
	# url = sys.argc > 0 ? sys.argv[1] : 'https://paypal.eightfold.ai/careers?query=Java&location=San%20Jose&pid=274894578339'

	driver = webdriver.Chrome(executable_path=r"/Volumes/trash/trash/chromedriver.mac64.intel.114.0.5735.90")
	print(url)

	driver.get(url)
	print(driver.title)

	tagName = "div" if len(sys.argv) < 3 else str(sys.argv[2])
	attrName = "role" if len(sys.argv) < 4 else str(sys.argv[3])
	attrValue = "button" if len(sys.argv) < 5 else str(sys.argv[4])

	time.sleep(1)


	href_urls = []

	for link in driver.find_elements_by_tag_name(tagName):

		if (not link.get_attribute('role') == 'button'):
			continue;

		
		driver.execute_script("arguments[0].click()", link)


		out = driver.execute_script("return window.location.href")
# 		print(str(out))
# 		out2 = driver.execute_script("return arguments[0]", link)
# 		print(link.find_elements_by_xpath('.//div/*')[0].get_attribute('innerHTML'))
# 		print(link.find_elements_by_css_selector("*"))
		t = link.find_elements_by_class_name("position-title")
		t2 = ""
		if (t is not None and len(t) > 0):
			t2 = t[0].get_attribute('innerHTML')
			print('{message: <26}'.format(message=t2) + "\t" + out)
# 		print(link.find_elements_by_tag_name("div"))

		

		time.sleep(1)
		href_urls.append('{message: <32}'.format(message=out) + "\t" + t2)
			# get meta where org="og:url"  "content" value

	xs = [x for x in href_urls if x is not None]

	for element in sorted(xs):
		print(element)

finally:        
	driver.close()
