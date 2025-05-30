#!/bin/sh

#----------------------------------------------------------------------------
# DESCRIPTION		
# DATE				[:VIM_EVAL:]strftime('%Y-%m-%d')[:END_EVAL:]
# AUTHOR			ss401533@gmail.com                                           
#----------------------------------------------------------------------------

#set -e
set -o errexit

test $# -gt 0 && echo "args given" || echo "no args"
# TODO: string comparison check (both ways)

cat <<EOF | batcat --plain --paging=never --language sh --theme TwoDark
https://www.google.com/about/careers/applications/jobs/results?hl=en_US&q=java%20software&company=YouTube&company=Google&target_level=MID&degree=MASTERS&degree=BACHELORS&employment_type=FULL_TIME&jlo=en_US&location=Mountain%20View%2C%20CA%2C%20USA&location=San%20Jose%2C%20CA%2C%20USA&location=Sunnyvale%2C%20CA%2C%20USA&skills=java&page=2
EOF


