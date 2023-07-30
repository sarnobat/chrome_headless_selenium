#!/bin/sh

#----------------------------------------------------------------------------
# DESCRIPTION		
# DATE				[:VIM_EVAL:]strftime('%Y-%m-%d')[:END_EVAL:]
# AUTHOR			ss401533@gmail.com                                           
#----------------------------------------------------------------------------


cat <<EOF | batcat --plain --paging=never --language sh --theme TwoDark
get job urls
------------
python3 /Volumes/git/github/chrome_headless_selenium/job_scraper/urls.py "${@:-https://jobs.netflix.com/search?q=java}" | tee /tmp/urls.txt

get job titles (from urls)
-------------------------
cat /tmp/urls.txt | grep /jobs/ |  xargs --delimiter '\n' -I% python3 -c "import bs4, requests; print('{message: <64}'.format(message=(bs4.BeautifulSoup(requests.get('%').content).title.text)) + '%')" | tee /tmp/out2.txt
EOF
