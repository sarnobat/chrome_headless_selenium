# This doesn't work. 
cat ~/sarnobat.git/yurl_queue_httpcat.txt  | perl -pe 's{.*http}{http}g' | grep -v null | grep -v jpg | DISPLAY=:99 xargs -n 1 -I% groovy headless.groovy ~/github/chrome_headless/chromedriver_linux64 "%"  | grep 'title>' | tee titles.txt
