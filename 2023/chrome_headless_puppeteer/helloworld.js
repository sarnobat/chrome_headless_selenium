// First:
// npm i --save puppeteer

// node index.js 'ws://127.0.0.1:9222/devtools/browser/a144b173-96bf-4bb4-8c0a-9e25c1992943'

const puppeteer = require('puppeteer');

(async() => {
	// server url comes from server output line:
	//
	// 		DevTools listening on ws://127.0.0.1:9222/devtools/browser/a144b173-96bf-4bb4-8c0a-9e25c1992943
	//
	const wsChromeEndpointurl = process.argv[2];
	
	const browser = await puppeteer.connect({
		browserWSEndpoint: wsChromeEndpointurl,
	});

	console.log(await browser.version());

	const page = await browser.newPage();
	await page.goto('https://www.chromestatus.com', {waitUntil: 'networkidle2'});

})();
