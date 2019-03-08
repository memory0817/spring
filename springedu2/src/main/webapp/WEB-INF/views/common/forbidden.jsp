<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
  <meta http-equiv="refresh" content="3; url=/">
  <title>403 Forbidden</title>
<!--     <link rel="stylesheet" href="style.css"> -->
	<style>
		.gate {
		  width: 300px;
		  margin: auto;
		  display: block;
		  position: relative;
		  top: 150px;
		  animation-name: gate-slam;
		  animation-duration: 1s;
		}
		.stonearchway {
		  width: 400px;
		  position: absolute;
		  top: 25%;
		  left: 50%;
		  transform: translate(-50%, 0);
		  margin: auto;
		  display: block;
		}
		body {
		  background: #1a1a1a;
		  position: relative;
		  margin: 0;
		}
		
		@keyframes gate-slam {
		  0% {top: -1000px;}
		  100% {top: 20px;}
		}
		
		@keyframes text-appear {
		  0% {opacity: 0;}
		  50% {opacity: 0;}
		  100% {opacity: 1;}
		}
		
		.forbiddenmessage {
		  color: white;
		  font-family: arial;
		
		  position: absolute;
		  top: 50%;
		  left: 50%;
		  transform: translate(-50%, -50%);
		  text-align: center;
		
		  animation-name: text-appear;
		  animation-duration: 2.5s;
		}
		
		h1, h3 {
		  margin: 0;
		}		
	</style>	
</head>
<body>

  <svg class="gate" id="Layer_1" data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 390 536.52">
  <title>Gate</title>
  <rect x="0.5" y="0.5" width="39" height="500" style="fill:#333"/>
  <path d="M669,304V803H631V304h38m1-1H630V804h40V303Z" transform="translate(-630 -303)" style="fill:#333"/>
  <rect x="70.5" y="0.5" width="39" height="500" style="fill:#333"/>
  <path d="M739,304V803H701V304h38m1-1H700V804h40V303Z" transform="translate(-630 -303)" style="fill:#333"/>
  <rect x="140.5" y="0.5" width="39" height="500" style="fill:#333"/>
  <path d="M809,304V803H771V304h38m1-1H770V804h40V303Z" transform="translate(-630 -303)" style="fill:#333"/>
  <rect x="210.5" y="0.5" width="39" height="500" style="fill:#333"/>
  <path d="M879,304V803H841V304h38m1-1H840V804h40V303Z" transform="translate(-630 -303)" style="fill:#333"/>
  <rect x="280.5" y="0.5" width="39" height="500" style="fill:#333"/>
  <path d="M949,304V803H911V304h38m1-1H910V804h40V303Z" transform="translate(-630 -303)" style="fill:#333"/>
  <rect x="350.5" y="0.5" width="39" height="500" style="fill:#333"/>
  <path d="M1019,304V803H981V304h38m1-1H980V804h40V303Z" transform="translate(-630 -303)" style="fill:#333"/>
  <rect x="0.5" y="371.5" width="389" height="39" style="fill:#333"/>
  <path d="M1019,675v38H631V675h388m1-1H630v40h390V674Z" transform="translate(-630 -303)" style="fill:#333"/>
  <rect x="0.5" y="90.5" width="389" height="39" style="fill:#333"/>
  <path d="M1019,394v38H631V394h388m1-1H630v40h390V393Z" transform="translate(-630 -303)" style="fill:#333"/>
  <polygon points="0.85 501.5 39.15 501.5 20 535.5 0.85 501.5" style="fill:#333"/>
  <path d="M668.29,805,650,837.49,631.71,805h36.58m1.71-1H630l20,35.52L670,804Z" transform="translate(-630 -303)" style="fill:#333"/>
  <polygon points="70.86 501.5 109.14 501.5 90 535.5 70.86 501.5" style="fill:#333"/>
  <path d="M738.29,805,720,837.49,701.71,805h36.58m1.71-1H700l20,35.52L740,804Z" transform="translate(-630 -303)" style="fill:#333"/>
  <polygon points="140.85 501.5 179.15 501.5 160 535.5 140.85 501.5" style="fill:#333"/>
  <path d="M808.29,805,790,837.49,771.71,805h36.58m1.71-1H770l20,35.52L810,804Z" transform="translate(-630 -303)" style="fill:#333"/>
  <polygon points="210.85 501.5 249.15 501.5 230 535.5 210.85 501.5" style="fill:#333"/>
  <path d="M878.29,805,860,837.49,841.71,805h36.58m1.71-1H840l20,35.52L880,804Z" transform="translate(-630 -303)" style="fill:#333"/>
  <polygon points="280.86 501.5 319.14 501.5 300 535.5 280.86 501.5" style="fill:#333"/>
  <path d="M948.29,805,930,837.49,911.71,805h36.58m1.71-1H910l20,35.52L950,804Z" transform="translate(-630 -303)" style="fill:#333"/>
  <polygon points="350.86 501.5 389.14 501.5 370 535.5 350.86 501.5" style="fill:#333"/>
  <path d="M1018.29,805,1000,837.49,981.71,805h36.58m1.71-1H980l20,35.52L1020,804Z" transform="translate(-630 -303)" style="fill:#333"/>
  </svg>
  <img class="stonearchway" src="https://preview.ibb.co/nvHe1e/Stone_Archway.png">

  <div class="forbiddenmessage">
    <h1>403</h1>
    <h3>FORBIDDEN</h3>
  </div>
</body>
</html>