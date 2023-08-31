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

cat <<'EOF' | batcat --plain --paging=never --language sh --theme TwoDark
Thanks to:
https://medium.com/@jaredpotter1/connecting-puppeteer-to-existing-chrome-window-8a10828149e0

Server
------
/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome --remote-debugging-port=9222 --no-first-run --no-default-browser-check --user-data-dir=/tmp/chrome2/ 2>&1 | tee /tmp/chrome_headless.err.log

Client
------
cd /Volumes/git/github/chrome_headless_selenium/chrome_headless_puppeteer && node index.js `cat /tmp/chrome_headless.err.log | awk '/DevTools listening/{print $4}'`

Conclusion: this is still a separate browser to my main one with my bookmarks. It's too closed an environment even though you can click manually. Greasemonkey, while cumbersome, is interoperable.
EOF
