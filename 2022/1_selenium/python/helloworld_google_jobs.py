from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from webdriver_manager.chrome import ChromeDriverManager
import time

options = Options()
options.binary_location = "/Volumes/apps/Apps/Google Chrome.app/Contents/MacOS/Google Chrome"

driver = webdriver.Chrome(service=Service(ChromeDriverManager(driver_version="137.0.7151.55").install()), options=options)
driver.get("https://www.google.com/search?num=10&sca_esv=ccd6930778b679eb&udm=8&sxsrf=AE3TifPGi3MqcAhip8htPYh_0lViQAmh-g%5C%3A1748302928481&q=java%5C%20jobs%5C%20new%5C%20jersey%5C%20Full%5C%20time%5C%20Full%5C%20time&uds=AOm0WdE2fekQnsyfYEw8JPYozOKzaXBUIHF2MdXIt8a5916iJgAvAbYuAybvl9gk5ndWSXisolfyBXqD0LnMKSpv4Br2SXLRJqPWHTSyWZUrTR2xZ_Pf9JvdO1gnWq_ardkPdFI7_lGFZjLtByXA1bT_a02Fx05JGLo9wHdEjdzS4EzzjuQdxk169oiqx25_JhkXS2EzHBY4QRbMVnYBCL1xXazyQglHGKrnm4TsarfKxjFuCkS2VG0IzwYx0pmj88gVC1W3iWcihvrx4rMhhVCAhxx7UT9WVLw1dtJeYCEvNgEpf3H90f2heNl5X9bEiagzNGpM4eg1F9KilrLgrw-IMhfxh4h88Q&ved=2ahUKEwj45ov4p8KNAxWqN2IAHQE8APkQmv0KegQIEhAG&jbr=sep:0")
print(driver.title)
# Scroll to bottom 5 times
for i in range(5):
    print(f"\n--- Scroll {i + 1} ---")
    
    # Scroll to bottom
    driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
    
    # Wait for new content to load (adjust if site loads slower)
    time.sleep(2)
    
    # Print visible text
    print(driver.find_element("tag name", "body").text)


# print(driver.find_element("tag name", "body").text)


driver.quit()
