// First:
// npm i --save puppeteer

const puppeteer = require('puppeteer');


(async() => {
	const wsChromeEndpointurl = 'ws://127.0.0.1:9222/devtools/browser/c81a5645-4c9a-4a60-bf40-525569ecf062';
	const browser = await puppeteer.connect({
		browserWSEndpoint: wsChromeEndpointurl,
	});

	console.log(await browser.version());

	const page = await browser.newPage();
	await page.goto('https://www.chromestatus.com', {waitUntil: 'networkidle2'});

})();
