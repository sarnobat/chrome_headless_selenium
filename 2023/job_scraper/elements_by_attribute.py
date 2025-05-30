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

href_urls = []

try:

	url = 'https://paypal.eightfold.ai/careers?query=Java&location=San%20Jose' if len(sys.argv) < 2 else sys.argv[1]

# 	url = {True: sys.argv[1], False: 'https://paypal.eightfold.ai/careers?query=Java&location=San%20Jose&pid=274894578339'} [len(sys.argv) > 1]
	# url = sys.argc > 0 ? sys.argv[1] : 'https://paypal.eightfold.ai/careers?query=Java&location=San%20Jose&pid=274894578339'

	driver = webdriver.Chrome(executable_path=r"/Volumes/trash/trash/chromedriver.mac64.intel.114.0.5735.90")

	# Be careful about changing this, the job list won't show up
	driver.set_window_size(1200, 600)

# 	print(url)

	driver.get(url)
# 	print(driver.title)

	tagName = "div" if len(sys.argv) < 3 else str(sys.argv[2])
	attrName = "role" if len(sys.argv) < 4 else str(sys.argv[3])
	attrValue = "button" if len(sys.argv) < 5 else str(sys.argv[4])

	time.sleep(3)
	driver.execute_script("window.scrollTo(0,document.body.scrollHeight)")

	for x in range(6):
		l = driver.find_elements_by_class_name("show-more-positions")[0];
		time.sleep(3)
		driver.execute_script("arguments[0].click();", l);
		time.sleep(3)
		driver.execute_script("window.scrollTo(0,document.body.scrollHeight)")
		time.sleep(3)

	time.sleep(3)


	for link in driver.find_elements_by_class_name('position-card'):

# 		print("[debug] 0 link = " + link.get_attribute('outerHTML'), file=sys.stderr)
		print("[debug] 0 link = " + link.tag_name, file=sys.stderr)

		time.sleep(3)
		if (not link.get_attribute('role') == 'button'):
# 			print("[debug] 0.05 role = " + link.get_attribute('role'), file=sys.stderr)
# 			driver.execute_script("window.scrollTo(0,document.body.scrollHeight)")
# 			time.sleep(3)
# 			print("[debug] 0.1", file=sys.stderr)
			l = driver.find_elements_by_class_name("show-more-positions")[0];
# 			print("[debug] 0.2", file=sys.stderr)
# # 			l.click();
# 			print("[debug] 0.3", file=sys.stderr)
			driver.execute_script("arguments[0].click();", l);
# 			print("[debug] 0.4", file=sys.stderr)
			continue;
		print("[debug] 0.5", file=sys.stderr)

		try:
			driver.execute_script("arguments[0].click()", link)

			time.sleep(3)
			print("[debug] 1", file=sys.stderr)

			out = driver.execute_script("return window.location.href")
			url = out
			print("[debug] 2", file=sys.stderr)
			time.sleep(3)
	# 		print(str(out))
	# 		out2 = driver.execute_script("return arguments[0]", link)
	# 		print(link.find_elements_by_xpath('.//div/*')[0].get_attribute('innerHTML'))
	# 		print(link.find_elements_by_css_selector("*"))
			t = link.find_elements_by_class_name("position-title")
			print("[debug] 3", file=sys.stderr)
			t2 = ""
			if (t is not None and len(t) > 0):
				print("[debug] 4", file=sys.stderr)
				t2 = t[0].get_attribute('innerHTML')
				print( '{message: <48}'.format(message=t2.strip()) + "\t" + url)
				print( "[debug] 4.5 " + '{message: <48}'.format(message=t2).strip() + "\t" + url, file=sys.stderr)
	# 		print(link.find_elements_by_tag_name("div"))
			print("[debug] 5", file=sys.stderr)
		

			time.sleep(2)
			print("[debug] 6", file=sys.stderr)
			href_urls.append('{message: <32}'.format(message=out) + "\t" + t2)
			print("[debug] 7 - success: " + t2, file=sys.stderr)
			sys.stdout.flush()
				# get meta where org="og:url"  "content" value
		except:
			print("error 1: ", file=sys.stderr)

except Exception as inst:
	print("[debug] 20 " + str(inst), file=sys.stderr)
finally:
	print("[debug] 21", file=sys.stderr)
	driver.close()


print("[debug] 11", file=sys.stderr)
xs = [x for x in href_urls if x is not None]
print("[debug] 12", file=sys.stderr)
for element in sorted(xs):
# 	print("[debug] 13", file=sys.stderr)
	print(element)
# 	print("[debug] 14", file=sys.stderr)

print("[debug] 15 finished entire script", file=sys.stderr)
