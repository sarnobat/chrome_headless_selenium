#!/bin/sh

#----------------------------------------------------------------------------
# DESCRIPTION		
# DATE				[:VIM_EVAL:]strftime('%Y-%m-%d')[:END_EVAL:]
# AUTHOR			ss401533@gmail.com                                           
#----------------------------------------------------------------------------


cat <<EOF | batcat --plain --paging=never --language sh --theme TwoDark
get job urls
------------
python3 elements_by_attribute.py

get job titles (from urls)
-------------------------
python3 elements_by_attribute.py

EOF
