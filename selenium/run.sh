
########
## nuc
########
# This works over SSH on nuc (but not barebones) with no -X. 99 corresponds to port 6099.
Xvfb :99 -ac
echo 'www.bbc.co.uk' 	| DISPLAY=:99 groovy headless.groovy ~/github/chrome_headless/chromedriver_linux64 | tee out.html

# This works on nuc with chromedriver_linux64 if I use ssh -X (which sets DISPLAY to localhost:10.0) with the ssh client being my Macbook pro
echo "www.bbc.co.uk"    | DISPLAY=:99 groovy headless.groovy ~/github/chrome_headless/chromedriver_linux64 | tee out.html
echo "www.teamtalk.com" | DISPLAY=:99 groovy biggest_image.groovy | tee biggest_image.txt

########
## Mac
########

# This works on Mac OS locally
DISPLAY=:99 groovy headless.groovy ~/github/chrome_headless/chromedriver_mac32    'www.bbc.co.uk' | tee out.html

# Trying to run this over SSH will only work with -X. So you can't do this as a pure command line script. Though with xvfb I think it's possible.
groovy headless.groovy ~/github/chrome_headless/chromedriver_mac 'www.bbc.co.uk' | tee out.html

