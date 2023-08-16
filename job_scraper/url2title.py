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
try:
	url = str(sys.argv[1]) or "https://www.google.com/"
# 	print(sys.argv[1] or "https://www.google.com/")
	# driver.get("https://www.google.com")
	driver.get(url)

	time.sleep(5)

# 	WebDriverWait(driver, 5)
	print('{message: <60}'.format(message=driver.title) + url)
		
# 	driver.close()

finally:
	driver.close()
