#!/bin/sh

#----------------------------------------------------------------------------
# DESCRIPTION		
# DATE				[:VIM_EVAL:]strftime('%Y-%m-%d')[:END_EVAL:]
# AUTHOR			ss401533@gmail.com                                           
#----------------------------------------------------------------------------

cat <<EOF | batcat --plain --paging=never --language sh --theme TwoDark
get job urls
------------
EOF
cat <<EOF | tee /tmp/jobs_oracle_list.sh | batcat --plain --paging=never --language sh --theme TwoDark
python3 /Volumes/git/github/chrome_headless_selenium/job_scraper/urls.py 'https://careers.oracle.com/jobs/#en/sites/jobsearch/requisitions?keyword=java&lastSelectedFacet=POSTING_DATES&location=Santa+Clara%2C+CA%2C+United+States&locationId=100000579041779&locationLevel=city&mode=location&radius=25&radiusUnit=MI&selectedCategoriesFacet=300000001917356&selectedLocationsFacet=100000579040474&selectedPostingDatesFacet=7' | tee /tmp/urls_oracle_raw.txt | grep /job/ | perl -pe 's{..keyword=.*}{}' | sort -n | tee /tmp/urls_oracle.txt
EOF

cat <<EOF | batcat --plain --paging=never --language sh --theme TwoDark

get job titles (from urls)
-------------------------
EOF
cat <<EOF | tee /tmp/jobs_oracle_details.sh | batcat --plain --paging=never --language sh --theme TwoDark
cat /tmp/urls_oracle.txt |  xargs --delimiter '\n' -n 1 python3 url2title.py | tee /tmp/jobs.txt
EOF

cat <<'EOF' | batcat --plain --paging=never --language sh --theme TwoDark

Quick start
-----------
sh /tmp/jobs_oracle_list.sh | xargs -n 1 -d'\n' sh /tmp/jobs_oracle_details.sh
EOF
