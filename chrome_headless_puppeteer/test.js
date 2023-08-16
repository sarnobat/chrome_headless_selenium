// First:
// npm i --save puppeteer

const puppeteer = require('puppeteer');

(async() => {
  const browser = await puppeteer.launch({headless: false , slowMo: 50,});

  console.log(await browser.version());

  const page = await browser.newPage();
  await page.goto('https://www.chromestatus.com', {waitUntil: 'networkidle2'});
  
   
  await browser.close();
})();
