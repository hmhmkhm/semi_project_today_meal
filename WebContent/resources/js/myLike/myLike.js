
(function() {
	let body = document.querySelector("body"),
		dimmed = body.querySelector(".dimmed"),
		modal = body.querySelector("#modal");

	let likeList = body.querySelector(".like_list"),
		listHeader = likeList.querySelector(".list_header"),
		selectAllInput = listHeader.querySelector("#select_all"),
		selectDeleteButton = listHeader.querySelector("button"),
		listBody = likeList.querySelector(".list_body"),
		itemCount = listBody.querySelectorAll(".item").length,
		checkBoxes = listBody.querySelectorAll("input[type=checkbox]");
	
	let modalForm = modal.querySelector("form");

	let checkCount = 0;
	let selectItem = [];

	function init() {
		selectAllInput.addEventListener("change", selectAllChangeEventHandler);
		selectDeleteButton.addEventListener("click", selectDeleteButtonClickEventHandler);
		listBody.addEventListener("click", listBodyClickEventHandler);
		modal.addEventListener("click", modalClickEventHandler);
	}

	function selectAllChangeEventHandler() {
		if(selectAllInput.classList.contains("active")) {
			deselectAll();
		} else {
			selectAll();
		}
	}

	function selectAll() {
		selectAllInput.classList.add("active");
		selectDeleteButton.removeAttribute("disabled");
		checkCount = itemCount;

		for(let checkBox of checkBoxes) {
			checkBox.classList.add("active");
			
			let likeNo = checkBox.getAttribute("data-like-no");
			if(selectItem.indexOf(likeNo) < 0) {
				selectItem.push(likeNo);
			}
		}
	}

	function deselectAll() {
		selectAllInput.classList.remove("active");
		selectDeleteButton.setAttribute("disabled", true);
		checkCount = 0;

		for(let checkBox of checkBoxes) {
			checkBox.classList.remove("active");
		}
		selectItem.length = 0;
	}

	function selectDeleteButtonClickEventHandler() {
		openModal();
		for(let select of selectItem) {
			modalForm.insertAdjacentHTML("beforeend", "<input type='hidden' name='lno' value='" + select + "'>");
		}
		modal.classList.add("delete_all");
	}

	function openModal() {
		body.classList.add("modal_open");
		dimmed.classList.add("show");
	}

	function closeModal() {
		body.classList.remove("modal_open");
		dimmed.classList.remove("show");
		modal.classList.remove("cart");
		modal.classList.remove("delete");
		modal.classList.remove("delete_all");
	}

	function listBodyClickEventHandler(e) {
		if(e.target.tagName == "INPUT") {
			if(e.target.classList.contains("active")) {
				deselectRow(e.target);
			} else {
				selectRow(e.target);
			}
		}

		if(e.target.tagName == "BUTTON") {
			if(e.target.classList.contains("option")) {
				optionButtonToggleEventHandler(e.target);
			} else if(e.target.classList.contains("cart")) {
				
			} else if(e.target.classList.contains("delete")) {
				deleteButtonClickEventHandler(e.target);
			}
		}
	}

	function selectRow(input) {
		input.classList.add("active");
		selectDeleteButton.removeAttribute("disabled");

		selectItem.push(input.getAttribute("data-like-no"));
		console.log(selectItem)

		if(++checkCount == itemCount) {
			selectAllInput.classList.add("active");
		}
	}

	function deselectRow(input) {
		input.classList.remove("active");

		if(--checkCount != itemCount) {
			selectAllInput.classList.remove("active");
		}

		selectItem.splice(selectItem.indexOf(input.getAttribute("data-like-no")), 1);

		if(checkCount == 0) {
			selectDeleteButton.setAttribute("disabled", true);
		}
	}

	function optionButtonToggleEventHandler(optionButton) {
		let optionArea = optionButton.closest(".option_area");

		if (optionArea.classList.contains("layer_open")) {
			optionArea.classList.remove("layer_open");
		} else {
			optionArea.classList.add("layer_open");
		}
	}

	function deleteButtonClickEventHandler(deleteButton) {
		openModal();
		modalForm.insertAdjacentHTML("beforeend", "<input type='hidden' name='lno' value='" + deleteButton.getAttribute("data-like-no") + "'>");
		modal.classList.add("delete");
	}

	function modalClickEventHandler(e) {
		let button = e.target;

		if(button.tagName == "BUTTON") {
			if(button.classList.contains("confirm_button")) {
				if(modal.classList.contains("delete_all")) {
					modalForm.action = "/today_meal/like/updateList";
					modalForm.submit();
				} else {
					modalForm.action = "/today_meal/like/updateList";
					modalForm.submit();
				}
			}
			modalForm.innerHTML = "";
			closeModal();
		}
	}

	window.addEventListener("DOMContentLoaded", init);	
})();
