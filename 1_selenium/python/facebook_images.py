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

chrome_options = webdriver.chrome.options.Options()
chrome_options.add_argument("--disable-notifications");
driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()),options=chrome_options)

url="https://www.facebook.com/"
driver.get(url)
time.sleep(3)
ActionChains(driver).send_keys_to_element(driver.find_elements("xpath", '//input[@id="email"]')[0], "ss533@cornell.edu").perform()
ActionChains(driver).send_keys_to_element(driver.find_elements("xpath", '//input[@id="pass"]')[0], "aize2FEN!").perform()
driver.find_elements("xpath", '//button[@type="submit"]')[0].click()
time.sleep(10)



driver.get("https://www.facebook.com/megha.panchamukhi.7/photos_by")
time.sleep(10)
print("1")

elems = driver.find_elements("xpath", "//a[contains(@href,'photo.php')]")
for elem in elems:
	print(elem.get_attribute("href"))
elems[0].click()

print("2")
# //*[@id="mount_0_0_UL"]/div/div[1]/div/div[4]/div/div/div[1]/div/div[4]/div/div/div[1]/div[1]/div/div/div/div/div/div/div[1]/div/a/div[2]/div/div/span/text()
nextPhoto = True
while (nextPhoto):
	time.sleep(5)
	elems = driver.find_elements("xpath", "//div[contains(@aria-label,'Actions for this post')]")
	elems[0].click()
	time.sleep(5)
	# e = driver.find_elements("xpath", "//span[contains(text(),'Download')]/../../../../..")[0]
	e = driver.find_elements("xpath", "//a[@download]")[0]
	# e = elems[0].get_parent().get_parent().get_parent().get_parent()
# 	print(e.get_attribute("outerHTML"))
	e.click()
	time.sleep(5)	
# 	elem = driver.find_elements("xpath", "//div[contains(@aria-label,'Next photo')]")[0]
# 	elem = driver.find_elements("xpath", "//div/div/div/i[@data-visualcompletion]")[0]
# 	elem = driver.find_elements("xpath", '//*[@id="mount_0_0_UL"]/div/div[1]/div/div[4]/div/div/div[1]/div/div[3]/div[2]/div/div[2]/div/div[1]/div/div[1]/div[3]/div/div/div/i')[0]
# 	elem = driver.find_elements("xpath", "//div[contains(@aria-label,'Next photo')]/div/div")[0]
# 	print(elem.get_attribute("outerHTML"))
# 	nextPhoto = elem != None
# 	if not nextPhoto:
# 		break
# 	elem.click()
	driver.find_elements("xpath", "//body")[0].send_keys(Keys.RIGHT)

