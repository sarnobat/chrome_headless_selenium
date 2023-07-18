# pip3 install selenium
# pip3 install webdriver-manager
# /Volumes/numerous/usr/local/homebrew/lib/python3.9/site-packages

from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
import sys

from selenium import webdriver
from selenium.webdriver.common.by import By

def dump_urls(driver, url):
    driver.get(url)
    hrefs = []
    for a in driver.find_elements(By.XPATH, "//a"):
        href = a.get_attribute("href")
        if href is not None:
            hrefs.append(href)
    return hrefs

if __name__ == "__main__":
    url = "https://www.tiktok.com/@ppwyang_"
    driver = webdriver.Chrome()
    hrefs = dump_urls(driver, url)
    print(hrefs)
    driver.quit()