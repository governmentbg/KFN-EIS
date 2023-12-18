This is a [Next.js](https://nextjs.org/) project bootstrapped with [`create-next-app`](https://github.com/vercel/next.js/tree/canary/packages/create-next-app).

## Getting Started
First, download and install Node.js LTS:
[https://nodejs.org/]

Second, install node version 16.13.1 via NVM:
In order to use NVM:

1. For Windows:
- download through this link:
- https://github.com/coreybutler/nvm-windows/releases/download/1.1.11/nvm-setup.exe

Install it through the downloaded installer;

2. For Linux or macOS follow these steps:
 - Open your terminal and run this command to download the installation script:
 ```bash
 curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.35.3/install.sh | bash
```
- Close and reopen your terminal to apply the changes to your shell configuration file.
- Verify that NVM is installed by running this command:
```bash
command -v nvm 
```
This should output nvm if the installation was successful.


3. Restart the computer after the installation is done; 

4. Then install the proper node version through the command below:
```bash
nvm install 16.13.1
```

Third, use node version 16.13.1 via NVM:
```bash
nvm use 16.13.1
```

Fourth, install the needed packages
```bash
npm install
```

That's it! The project is set!

There are two modes that can be started now:
- Development 
```bash
npm run dev
# or
yarn dev
```
-Production
```bash
npm run build && npm run start# or
yarn build && yarn start
```

## Important note
In order to submit any service (Service Request), like:
- reports;
- complaints and etc.

BISS Server must be started locally. This can be done in a few steps:

First step:
Clone or download the 'fsc-document-signing-service' repository from the URL below:
- https://bitbucket.scalefocus.com/projects/FSC/repos/fsc-document-signing-service/browse

Second step:
Open bash terminal in the following directory of the 'fsc-document-signing-service' cloned/downloaded repo: 
- fsc-document-signing-service/tools/BISS-mockup-server/

Run the following bash command in the opened directory:
- npm i && npm run start

Open https://localhost:53955/version and accept the risk in the browser then it should be ok for testing!

And that's it!
You are ready to consume the api of the started BISS server, submitting any service through the service catalog!



## How to access the UI
Open [http://localhost:3000](http://localhost:3000) with your browser to see the result.