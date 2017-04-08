/*******************************************************************************
 * jQuery Validate扩展验证方法 (linjq)
 ******************************************************************************/
$(function() {

	/*
	 * 小数验证，小数点位数按照max参数的小数点位数进行判断 只能输入数字
	 */
	$.validator.addMethod("isDecimal", function(value, element, params) {

		if (params != undefined && typeof (params) == "string"
				&& params.startsWith("[")) {
			params = eval(params);
		}
		// debugger;
		if (isNaN(params[0])) {
			console.error("参数错误，decimal验证的min只能为数字");
			return false;
		}
		if (isNaN(params[1])) {
			console.error("参数错误，decimal验证的max只能为数字");
			return false;
		}
		if (isNaN(params[2])) {
			console.error("参数错误，decimal验证的accuracy只能为数字");
			return false;
		}
		if (isNaN(value)) {
			return false;
		}
		// if(typeof(value) == undefined || value == "") {
		// return false;
		// }
		var min = Number(params[0]);
		var max = Number(params[1]);
		var testVal = Number(value);
		var testAbsVal = Math.abs(testVal);
		if (typeof (params[2]) == undefined || params[2] == 0) {
			var regX = /^\d+$/;
		} else {
			var regxStr = "^\\d+(\\.\\d{1," + params[2] + "})?$";
			var regX = new RegExp(regxStr);
		}
		// console.debug("regX: %o, value: %o, test: %o", regX, value,
		// regX.test(value));
		return this.optional(element)
				|| (regX.test(testAbsVal) && testVal >= min && testVal <= max);
	}, $.validator.format("请输入在{0}到{1}之间且保留小数点后{2}位的值"));

	/* 整数验证,只能输入数字 */
	$.validator.addMethod("isInt", function(value, element, params) {

		if (params != undefined && typeof (params) == "string"
				&& params.startsWith("[")) {
			params = eval(params);
		}
		// debugger;
		if (isNaN(params[0])) {
			console.error("参数错误，decimal验证的min只能为数字");
			return false;
		}
		if (isNaN(params[1])) {
			console.error("参数错误，decimal验证的max只能为数字");
			return false;
		}
		if (isNaN(value)) {
			return false;
		}
		var min = Number(params[0]);
		var max = Number(params[1]);
		var testVal = Number(value);
		var testAbsVal = Math.abs(testVal);
		// console.debug("regX: %o, value: %o, test: %o", regX, value,
		// regX.test(value));
		return this.optional(element) || (testVal >= min && testVal <= max);
	}, $.validator.format("请输入在{0}到{1}之间的整数"));

	/* 只允许输入字母 */
	$.validator.addMethod("isAlphabet", function(value, element) {
		return this.optional(element) || /^[a-zA-Z]+$/.test(value);
	}, $.validator.format("请输入字母"));

	/* 只允许输入字母或数字 */
	$.validator.addMethod("isAlphabetDigits", function(value, element) {
		return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
	}, $.validator.format("请输入字母或数字"));

	/* 条码验证 */
	jQuery.validator.addMethod("isBarcode",
			function(value, element) {
				var result = false;
				var length = value.length;
				var d = /^\d{13}$/.test(value);
				if (length == 13 && d) {
					var c1 = parseInt(value.charAt(0))
							+ parseInt(value.charAt(2))
							+ parseInt(value.charAt(4))
							+ parseInt(value.charAt(6))
							+ parseInt(value.charAt(8))
							+ parseInt(value.charAt(10));
					var c2 = (parseInt(value.charAt(1))
							+ parseInt(value.charAt(3))
							+ parseInt(value.charAt(5))
							+ parseInt(value.charAt(7))
							+ parseInt(value.charAt(9)) + parseInt(value
							.charAt(11))) * 3;
					var c3 = parseInt(value.charAt(12))
					var cc = 10 - (c1 + c2) % 10;
					if (cc == 10) {
						cc = 0;
					}
					if (cc == c3) {
						result = true;
					}
				}
				return this.optional(element) || result;
			}, "无效条码");

});
