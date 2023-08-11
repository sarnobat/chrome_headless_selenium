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
driver.set_window_size(800, 600)

url = 'https://paypal.eightfold.ai/candidate/login?domain=paypal.com' if len(sys.argv) < 2 else sys.argv[1]
driver.get(url)
print(driver.title)

time.sleep(5)

original_window = driver.current_window_handle

signInGoogle = driver.find_elements_by_class_name('provider-signin-button')[0]
signInGoogle.click()
time.sleep(2)

for window_handle in driver.window_handles:
	if window_handle != original_window:
		driver.switch_to.window(window_handle)
		break
print(driver.title)


textField = driver.find_elements(By.XPATH, '/html/body/div[1]/div[1]/div[2]/div/c-wiz/div/div[2]/div/div[1]/div/form/span/section/div/div/div[1]/div/div[1]/div/div[1]/input')[0]

# textField = driver.find_elements(By.XPATH, '//*[@id="identifierId"]')[0]

textField.send_keys("ss401533@gmail.com\n")
# driver.execute_script("arguments[0].click();", signInGoogle);

time.sleep(5)

# document.querySelector("#main > div > div.layout-row > div.col-md-6.full-height-container.ef-styles-2020 > div.form-container > button")

# try:
# 	href_urls = []
# 	for link in driver.find_elements_by_tag_name("a"):
# 		href = link.get_attribute("href")
# 		if href != None:
# 			href_urls.append(href)
# 
# 	xs = [x for x in href_urls if x is not None]
# 	# for element in href_urls if element is not None:
# 	for element in sorted(xs):
# 		print(element)
# 		
# # 	driver.close()
# 
# finally:
# 	driver.close()
