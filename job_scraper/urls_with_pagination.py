# pip3 install selenium
# pip3 install webdriver-manager
# /Volumes/numerous/usr/local/homebrew/lib/python3.9/site-packages

from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
import sys
import time

driver = webdriver.Chrome(executable_path=r"/Volumes/trash/trash/chromedriver.mac64.intel.114.0.5735.90")

driver.set_window_size(1200, 600)

# nextPath = "#yDmH0d > c-wiz > div > div:nth-child(2) > div > div > div.BiNgOe.E2Mxid > main > div > c-wiz > div > div.uF8gGf > div > div > div > div > button:nth-child(3)"
# document.querySelector(nextPath)

try:

	url = sys.argv[1] if len(sys.argv) > 1 else "https://www.google.com/about/careers/applications/jobs/results?hl=en_US&q=java%20software&company=YouTube&company=Google&target_level=MID&degree=MASTERS&degree=BACHELORS&employment_type=FULL_TIME&jlo=en_US&location=Mountain%20View%2C%20CA%2C%20USA&location=San%20Jose%2C%20CA%2C%20USA&location=Sunnyvale%2C%20CA%2C%20USA&skills=java&page=1"
	driver.get(url)
	time.sleep(10)
	print(driver.title)

	nextPageElem = driver.find_elements_by_class_name("VfPpkd-Bz112c-LgbsSe")[0];

	# nextPageElem.scrollIntoView()
	driver.execute_script("arguments[0].click();", nextPageElem);
	time.sleep(50)

	href_urls = []

	for link in driver.find_elements_by_tag_name("a"):
		href = link.get_attribute("href")
		if href != None:
			href_urls.append(href)
			print("[debug] " + href)

	xs = [x for x in href_urls if x is not None]

	for element in sorted(xs):
		print(element)

finally:
	driver.close()
