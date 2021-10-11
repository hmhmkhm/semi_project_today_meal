(function() {
	let body = document.querySelector("body"),
		container = document.querySelector("#container"),
		dimmed = body.querySelector(".dimmed"),
		modal = dimmed.querySelector("#modal");
	
	let payMentInner = modal.querySelector(".payment_inner").querySelector("div"),
		changeInner = modal.querySelector(".change_inner"),
		prevStatus = changeInner.querySelector(".prev_status"),
		changeStatusSelect = changeInner.querySelector(".change_status"),
		changeStatusList = changeStatusSelect.querySelectorAll("option"),
		changeInnerForm = changeInner.querySelector("form");

	let searchArea = container.querySelector(".search_area"),
		startYear = searchArea.querySelector("#start_year"),
		startMonth = searchArea.querySelector("#start_month"),
		startDay = searchArea.querySelector("#start_day"),
		endYear = searchArea.querySelector("#end_year"),
		endMonth = searchArea.querySelector("#end_month"),
		endDay = searchArea.querySelector("#end_day"),
		startInput = searchArea.querySelector("#start_date"),
		endInput = searchArea.querySelector("#end_date"),
		idInput = searchArea.querySelector("#id"),
		orderNumberInput = searchArea.querySelector("#orderNumber"),
		searchButton = searchArea.querySelector(".search_button");

	let resultArea = container.querySelector(".result_area"), 
		resultOption = resultArea.querySelector(".result_option"),
		selectAllInput = resultOption.querySelector("#select_all"),
		changeStatusButton = resultOption.querySelector("button"),
		resultTable = resultArea.querySelector("table"), 
		orderStatusSelect = resultTable.querySelector(".orderStatus"),
		orderStatusForm = orderStatusSelect.closest("form"),
		resultBody = resultTable.querySelector("tbody"), 
		resultCount = resultBody.querySelectorAll("tr").length,
		checkBoxes = resultBody.querySelectorAll("input[type=checkbox]");

	let checkCount = 0;
	let today = new Date();
	let selectItem = [];
	let orderState = 0;

	function init() {
		searchButton.addEventListener("click", searchButtonClickEventHandler);
		selectAllInput.addEventListener("change", selectAllChangeEventHandler);
		changeStatusButton.addEventListener("click", changeStatusButtonClickEventHandler);
		resultTable.addEventListener("click", resultTableClickEventHandler);
		orderStatusSelect.addEventListener("change", orderstatusSelectChangeEventHandler);
		searchArea.addEventListener("change", searchAreaChangeEventHandler);
		modal.addEventListener("click", modalClickEventListener);
	}

	function searchButtonClickEventHandler() {
		let startDate = new Date(startYear.value, startMonth.value-1, startDay.value),
			endDate = new Date(endYear.value, endMonth.value-1, endDay.value),
			id = idInput.value,
			orderNumber = orderNumberInput.value,
			status = true;

		if(startDate.getTime() > endDate.getTime()) {
			status = false;
			alert("입력하신 기간이 유효하지 않습니다.");
		} else if(id.length > 0 && !(/^[a-z][a-z\d]{5,11}$/.test(id))) {
			idInput.focus();
			alert("입력하신 아이디가 유효하지 않습니다.");
			status = false;
		} else if(orderNumber.length > 0 && orderNumber.length != 10) {
			status = false;
			alert("입력하신 주문번호가 유효하지 않습니다.");
			orderNumberInput.focus();
		}

		if(status) {
			startInput.setAttribute("value", startYear.value + (startMonth.value < 10 ? "0" : "") + startMonth.value + (startDay.value < 10 ? "0" : "") + startDay.value);
			endInput.setAttribute("value", endYear.value + (endMonth.value < 10 ? "0" : "") + endMonth.value + (endDay.value < 10 ? "0" : "") + endDay.value);
			searchArea.submit();
		}
	}

	function selectAllChangeEventHandler() {
		if(this.classList.contains("active")) {
			deselectAll();
		} else {
			selectAll();
		}
	}

	function selectAll() {
		let isSameState = true;
		
		if(selectItem.length == 0) {
			orderState = checkBoxes[0].getAttribute("data-order-state");
		}

		for(let checkBox of checkBoxes) {
			if(orderState != checkBox.getAttribute("data-order-state")) {
				isSameState = false;
				break;
			}
		}

		if(isSameState) {
			selectAllInput.classList.add("active");
			changeStatusButton.removeAttribute("disabled");
			checkCount = resultCount;

			for(let checkBox of checkBoxes) {
				checkBox.classList.add("active");
				
				let receiptNo = checkBox.getAttribute("data-receipt-no");
				if(selectItem.indexOf(receiptNo) < 0) {
					selectItem.push(receiptNo);
				}
			}
		} else {
			alert("전체 선택을 하기 위해서는 주문이 동일한 주문 상태여야 합니다.");
		}
	}

	function deselectAll() {
		selectAllInput.classList.remove("active");
		changeStatusButton.setAttribute("disabled", true);
		checkCount = 0;
		orderState = 0;

		for(let checkBox of checkBoxes) {
			checkBox.classList.remove("active");
		}
		selectItem.length = 0;
	}

	function changeStatusButtonClickEventHandler() {
		changeInnerForm.innerHTML = "";

		for(let select of selectItem) {
			changeInnerForm.insertAdjacentHTML("beforeend", "<input type='hidden' name='rno' value='" + select + "'>");
		}

		let status = false;
		for(let changeStatus of changeStatusList) {
			if(status) {
				changeStatus.removeAttribute("disabled");
			} else {
				changeStatus.setAttribute("disabled", true);
			}
			if(changeStatus.getAttribute("data-order-state") == orderState) {
				prevStatus.innerText = changeStatus.textContent;
				changeStatus.nextElementSibling.setAttribute("selected", true);
				status = true;
			}
		}

		openModal();
		modal.classList.add("change");
	}

	function openModal() {
		body.classList.add("modal_open");
		dimmed.classList.add("show");
	}

	function closeModal() {
		body.classList.remove("modal_open");
		dimmed.classList.remove("show");
		modal.classList.remove("payment");
		modal.classList.remove("change");
	}

	function resultTableClickEventHandler(e) {
		if(e.target.classList.contains("learn_more_button")) {
			learnMoreButtonClickEventHandler(e.target);
		}

		if(e.target.tagName == "INPUT") {
			if(e.target.classList.contains("active")) {
				deselectRow(e.target);
			} else {
				selectRow(e.target);
			}
		}
	}

	function orderstatusSelectChangeEventHandler() {
		orderStatusForm.submit();
	}
	
	function createQuery(query, key, value) {
		if(value != "") {
			query += ((query.length > 0) ? "&" : "") + key + "=" + value;
		}

		return query;
	}

	function learnMoreButtonClickEventHandler(learnMoreButton) {
		learnMoreButton.setAttribute("disabled", true);
		let query = createQuery("", "rno", learnMoreButton.getAttribute("data-receipt-no"));
		
		let xhr = new XMLHttpRequest();
		
		xhr.onreadystatechange = function() {
			if(xhr.readyState == 4) {
				if((xhr.status >= 200 && xhr.status < 300) || xhr.status == 304) {
					let responseTextJson = JSON.parse(xhr.responseText);
					payMentInner.innerHTML = "";
					appendModal(responseTextJson);
					learnMoreButton.removeAttribute("disabled");
				} else {
					console.log("ajax 통신 실패");
				}
			}
		}
		
		xhr.open("POST", "/today_meal/sale/detail");
		xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded;");
		console.log(query);
		xhr.send(query);

		openModal();
		modal.classList.add("payment");
	}
	
	function numberFormat(number) {
		let regexp = /\B(?=(\d{3})+(?!\d))/g;
		return number.toString().replace(regexp, ',');
	}
	
	function phoneFormat(phone) {
		return phone.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
	}
	
	function appendModal(detailData) {
		let html = "";
		html += "<div class='status_area'>";
		html += 	"<ul>";
		html += 		"<li>" + detailData.ono + "</li>"
		html += 		"<li>" + detailData.saleDate.substring(0, 10) + "</li>"
		html += 	"</ul>";
		html +=		"<div>";
		html +=			"<p>" + detailData.orderState + " 상태</p>";
		html +=		"</div>";
		html +=	"</div>";
		html +=	"<div class='product_area'>";
		html +=		"<h4>- 상품내역</h4>";
		html +=		"<table>";
		html +=			"<thead>";
		html +=				"<tr>";
		html +=					"<th scope='col'>제품명</th>";
		html +=					"<th scope='col'>제품 수량</th>";
		html +=					"<th scope='col'>가격</th>";
		html +=				"</tr>";
		html +=			"</thead>";
		html +=			"<tbody>";
		html +=				"<tr class='depth_1'>";
		html +=					"<td>" + detailData.product.pname + "</td>";
		html +=					"<td class='center'>" + numberFormat(detailData.product.buyQuantity) + "</td>";
		html +=					"<td class='center'>" + numberFormat(detailData.product.price * detailData.product.buyQuantity) + "</td>";
		html +=				"</tr>";
		for(let option of detailData.product.optionList) {
		html +=				"<tr class='depth_2'>";
		html +=					"<td>+ " + option.name + "</td>";
		html +=					"<td class='center'>" + numberFormat(option.buyQuantity) + "</td>";
		html +=					"<td class='center'>" + numberFormat(option.price * option.buyQuantity) + "</td>";
		html +=				"</tr>";
		}
		html +=			"</tbody>";
		html +=			"<tbody>"
		html +=				"<tr>";
		html +=					"<td>배송료</td>";
		html +=					"<td class='center'>1</td>";
		html +=					"<td class='center'>" + numberFormat(detailData.delivery.delivery_fee) + "</td>";
		html +=				"</tr>";
		html +=			"</tbody>";
		html +=			"<tbody>"
		html +=				"<tr>";
		html +=					"<td>적립금 사용</td>";
		html +=					"<td class='center'>1</td>";
		html +=					"<td class='center'>" + numberFormat(detailData.coin) + "</td>";
		html +=				"</tr>";
		html +=			"</tbody>";
		html +=			"<tfoot>";
		html +=				"<tr>";
		html +=					"<th scope='row' colspan='2'>합계</th>";
		html +=					"<td class='center'>" + numberFormat(detailData.orderSum) + "</td>";
		html +=				"</tr>";
		html +=			"</tfoot>";
		html +=		"</table>";
		html +=	"</div>";
		html +=	"<div class='list'>";
		html +=		"<h4>- 결제내역</h4>";
		html +=		"<div>";
		html +=			"<dl>";
		html +=				"<dt class='two_line'>거래<br> 고유 번호</dt>";
		html +=				"<dd>" + detailData.payment.impUid + "</dd>";
		html +=			"</dl>";
		html +=			"<dl>";
		html +=				"<dt>결제 수단</dt>";
		html +=				"<dd>" + detailData.payment.payMehtod + "</dd>";
		html +=			"</dl>";
		html +=			"<dl>";
		html +=				"<dt>결제금액</dt>";
		html +=				"<dd>" + detailData.payment.payment + "</dd>";
		html +=			"</dl>";
		html +=			"<dl>";
		html +=				"<dt>승인일시</dt>";
		html +=				"<dd>" + detailData.payment.paidAt + "</dd>";
		html +=			"</dl>";
		html +=			"<dl>";
		html +=				"<dt>결제상태</dt>";
		html +=				"<dd>" + detailData.payment.status + "</dd>";
		html +=			"</dl>";
		html +=		"</div>";
		html +=	"</div>";
		html +=	"<div class='list'>";
		html +=		"<h4>- 배송정보</h4>";
		html +=		"<div>";
		html +=			"<dl>";
		html +=				"<dt>이름</dt>";
		html +=				"<dd>" + detailData.delivery.name + "</dd>";
		html +=			"</dl>";
		html +=			"<dl>";
		html +=				"<dt>연락처</dt>";
		html +=				"<dd>" + phoneFormat(detailData.delivery.phone) + "</dd>";
		html +=			"</dl>";
		html +=			"<dl>";
		html +=				"<dt>주소</dt>";
		html +=				"<dd class='two_line'>" + detailData.delivery.address + "</dd>";
		html +=			"</dl>";
		html +=			"<dl>";
		html +=				"<dt class='two_line'>배송시<br>요청사항</dt>";
		html +=				"<dd class='two_line'>" + detailData.delivery.request + "</dd>";
		html +=			"</dl>";
		html +=		"</div>";
		html +=	"</div>";

		payMentInner.insertAdjacentHTML("beforeend", html);
	}

	function selectRow(input) {
		let isSameState = true;

		if(selectItem.length != 0 && orderState != input.getAttribute("data-order-state")) {
			isSameState = false;
		}
		
		if(isSameState) {
			input.classList.add("active");
			changeStatusButton.removeAttribute("disabled");
			selectItem.push(input.getAttribute("data-receipt-no"));
			orderState = input.getAttribute("data-order-state");

			if(++checkCount == resultCount) {
				selectAllInput.classList.add("active");
			}
		} else {
			alert("선택하신 주문의 상태가 기존에 선택된 주문 상태와 동일하지 않아 선택하실 수 없습니다.");
		}
	}

	function deselectRow(input) {
		input.classList.remove("active");

		if(--checkCount != resultCount) {
			selectAllInput.classList.remove("active");
		}
		
		selectItem.splice(selectItem.indexOf(input.getAttribute("data-receipt-no")), 1);

		if(checkCount == 0) {
			changeStatusButton.setAttribute("disabled", true);
			orderState = 0;
		}
	}

	function initMonthSelect(year, target) {
		for(let option of target.querySelectorAll("option")) {
			option.removeAttribute("disabled");
			option.removeAttribute("selected");

			if(!(year < today.getFullYear() || (year == today.getFullYear() && option.value-1 <= today.getMonth()))) {
				option.setAttribute("disabled", true);
			}
		}

		target[0].setAttribute("selected", true);
	}

	function initDaySelect(year, month, target) {
		let lastDay = 0,
			html = "";

		switch(month) {
			case 1 : case 3 : case 5 : case 7 : case 8 : case 10 : case 12 : lastDay = 31; break;
			case 4 : case 6 : case 9 : case 11 : lastDay = 30; break;
			case 2 : lastDay = ((year%4 == 0) && (year % 100 != 0) || (year%400 == 0) ? 29 : 28); break;
			}
		
		target.innerHTML = "";

		for(let i=1; i<=lastDay; i++) {
			html += "<option value='" + i + "' " + (i == 1 ? "selected " : "") + (year < today.getFullYear() || (year == today.getFullYear() && month-1 < today.getMonth()) || (year == today.getFullYear() && month-1 <= today.getMonth() && i <= today.getDate()) ? "" : "disabled") +  ">" + i + "</option>"
		}

		target.insertAdjacentHTML("beforeend", html);
	}

	function searchAreaChangeEventHandler(e) {
		if(e.target.tagName == "SELECT") {
			if(e.target.classList.contains("year")) {
				for(let option of e.target.querySelectorAll("option")) {
					option.removeAttribute("selected");
				}

				let yearTarget = e.target,
					monthTarget = null,
					dayTarget = null,
					year = e.target.value;

				if(e.target.getAttribute("id").indexOf("start") >= 0) {
					monthTarget = startMonth;
					dayTarget = startDay;
				} else {
					monthTarget = endMonth;
					dayTarget = endDay;
				}

				yearTarget.querySelector("option[value='" + year + "']").setAttribute("selected", true);
				initMonthSelect(year, monthTarget);
				initDaySelect(Number(year), 1, dayTarget);
			} else if(e.target.classList.contains("month")) {
				for(let option of e.target.querySelectorAll("option")) {
					option.removeAttribute("selected");
				}

				let monthTarget = e.target,
					dayTarget = null,
					year = 0,
					month = monthTarget.value;

				if(e.target.getAttribute("id").indexOf("start") >= 0) {
					dayTarget = startDay;
					year = startYear.value;
				} else {
					dayTarget = endDay;
					year = endYear.value;
				}
				monthTarget.querySelector("option[value='" + month + "']").setAttribute("selected", true);
				initDaySelect(Number(year), Number(month), dayTarget);
			}
		}
	}

	function modalClickEventListener(e) {
		if(e.target.tagName == "BUTTON") {
			if(modal.classList.contains("change") && e.target.classList.contains("confirm_button")) {
				changeInnerForm.insertAdjacentHTML("beforeend", "<input type='hidden' name='chageStatus' value='" + changeStatusSelect.options[changeStatusSelect.selectedIndex].getAttribute("data-order-state") + "'>");
				changeInnerForm.action = "/today_meal/sale/changeStatus" + window.location.href.substring(window.location.href.indexOf("?"));
				changeInnerForm.submit();
			}
			closeModal();
		}
	}

	window.addEventListener("DOMContentLoaded", init);
})();
