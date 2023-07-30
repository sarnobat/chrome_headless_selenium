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
cat <<EOF | tee /tmp/jobs_paypal_urls.sh | batcat --plain --paging=never --language sh --theme TwoDark
python3 elements_by_attribute.py | tee /tmp/jobs_paypal_urls.txt

EOF
cat <<EOF | batcat --plain --paging=never --language sh --theme TwoDark
get job titles (from urls)
-------------------------
EOF
cat <<EOF | tee /tmp/jobs_paypal_details.sh | batcat --plain --paging=never --language sh --theme TwoDark
python3 elements_by_attribute.py
EOF

cat <<EOF | batcat --plain --paging=never --language sh --theme TwoDark
Quick Start
-----------
EOF

cat <<EOF | batcat --plain --paging=never --language sh --theme TwoDark
sh /tmp/jobs_paypal_urls.sh | xargs -n 1 -d'\n' sh /tmp/jobs_paypal_details.sh | tee tmp/jobs_paypal.`date -I`.txt
EOF
