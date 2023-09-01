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

Batch
=====
	credits: https://medium.com/@jaredpotter1/connecting-puppeteer-to-existing-chrome-window-8a10828149e0

	Server
	------
	/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome --remote-debugging-port=9222 --no-first-run --no-default-browser-check --user-data-dir=/tmp/chrome2/ 2>&1 | tee /tmp/chrome_headless.err.log

	Client
	------
	cd /Volumes/git/github/chrome_headless_selenium/chrome_headless_puppeteer && node index.js `cat /tmp/chrome_headless.err.log | awk '/DevTools listening/{print $4}'`

	Conclusion: this is still a separate browser to my main one with my bookmarks. It's too closed an environment even though you can click manually. Greasemonkey, while cumbersome, is interoperable.

EOF

cat <<'EOF' | batcat --plain --paging=never --language javascript --theme gruvbox-dark
Interactive
===========
	credits: https://shuaiber.medium.com/interactive-puppeteer-setup-822f5ed2a874

	Server
	------
	
	cd /Volumes/git/github/chrome_headless_selenium/chrome_headless_puppeteer && node --inspect=127.0.0.1:9223

	Client
	------
	(From a REGULAR chrome browser)
	chrome://inspect/#devices
	"Discover network targets"
	Then the 9223 target should appear
	Click "inspect"
	
	Perform your REPL:

		page = await (await require('puppeteer').launch({ args: ["--no-sandbox"], headless: false })).newPage()
		page.goto("https://www.google.com")
		
	Walmart:
		page.goto("https://careers.walmart.com/results?q=&page=1&sort=date&jobCity=Sunnyvale&jobState=CA&jobCategory=00000159-75a2-d286-a3f9-7fa2bac60000,00000161-7bf4-da32-a37b-fbf7c59e0000&jobDepartmentCode=00000159-75a2-d286-a3f9-7fa2bac60000&expand=department,brand,type,rate&type=jobs")		
		(await page.$x('//a'))
		(await page.$x('//a[@class="job-listing__link"]'))
		(await page.$x('//a[@class="job-listing__link"]'))[0].click()



EOF
