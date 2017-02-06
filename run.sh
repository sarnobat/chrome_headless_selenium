# Trying to run this over SSH will only work with -X. So you can't do this as a pure command line script. Though with xvfb I think it's possible.

groovy headless.groovy ~/github/chrome_headless/chromedriver_mac 'www.bbc.co.uk' | tee out.html

# nuc
# This works on nuc with chromedriver_linux64 if I use ssh -X (which sets DISPLAY to localhost:10.0) with the ssh client being my Macbook pro
groovy headless.groovy ~/github/chrome_headless/chromedriver_linux64  'www.bbc.co.uk' | tee out.html
groovy biggest_image.groovy 'www.teamtalk.com' | tee biggest_image.txt

# This works over SSH on nuc (but not barebones) with no -X. 99 corresponds to port 6099.
Xvfb :99 -ac
DISPLAY=:99 groovy headless.groovy ~/github/chrome_headless/chromedriver_linux64  'www.bbc.co.uk' | tee out.html
