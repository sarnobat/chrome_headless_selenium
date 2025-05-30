from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from webdriver_manager.chrome import ChromeDriverManager

options = Options()
options.binary_location = "/Volumes/apps/Apps/Google Chrome.app/Contents/MacOS/Google Chrome"

driver = webdriver.Chrome(service=Service(ChromeDriverManager(driver_version="137.0.7151.55").install()), options=options)
driver.get("https://www.indeed.com")
print(driver.title)
driver.quit()
