from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from webdriver_manager.chrome import ChromeDriverManager
import time

# intitle: 
# allintitle: 
intext:
allintext:
# url = 'https://www.google.com/search?udm=8&q=java%5C%20jobs%5C%20new%5C%20jersey%5C%20Full%5C%20time%5C%20Full%5C%20time'
url = 'https://www.google.com/search?q=Java+jobs+new+york+full+time+salary+in+the+last+3+days&udm=8'

options = Options()
options.binary_location = "/Volumes/apps/Apps/Google Chrome.app/Contents/MacOS/Google Chrome"

driver = webdriver.Chrome(service=Service(ChromeDriverManager(driver_version="137.0.7151.55").install()), options=options)
driver.set_window_position(1000, 50)
driver.get(url)

time.sleep(35)

# print(driver.title)

for i in range(8):

    print(f"\n--- Scroll {i + 1} ---")

    driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")

    time.sleep(2)

    print(driver.find_element("tag name", "body").text)

driver.quit()

print(url)