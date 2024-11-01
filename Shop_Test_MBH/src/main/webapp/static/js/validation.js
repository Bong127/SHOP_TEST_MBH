/**
 *  유효성 검사 
 */
function checkProduct() {
	let form = document.product
	let productId = form.productId
	let name = form.name
	let unitPrice = form.unitPrice
	let unitsInStock = form.unitsInStock
	
	let msg = ''
	
	// 상품아이디 체크
	// - P숫자 6자리
	let productIdCheck = /^P[0-9]{6}$/
	msg = '상품 아이디는 "P6자리" 로 입력해주세요' 
	if( !check(productIdCheck, productId, msg) ) return false
	
	// 상품명 체크
	// - 2글자 이상 20글자 이하
	let nameCheck = /^.{2,20}$/
	msg = '상품명은 2~20자 이내로 입력해주세요'
	if( !check(nameCheck, name, msg) ) return false
	
	// 가격 체크
	// - 숫자로 1글자 이상
	let priceCheck = /^\d{1,}$/
	msg = '가격은 1글자 이상의 숫자로 입력해주세요'
	if( !check(priceCheck, unitPrice, msg) ) return false
	
	// 재고 체크
	// - 숫자로 1글자 이상
	let stockCheck = /^\d{1,}$/
	msg = '재고는 1글자 이상의 숫자로 입력해주세요'
	if( !check(stockCheck, unitsInStock, msg) ) return false
	
	return true
}

function checkUser() {
	let form = document.joinForm
	let id = form.id
	let name = form.name
	let pw = form.pw
	let pwConfirm = form.pw_confirm
	
	let msg = ''
	
	// 아이디 체크
	// - 영문자 또는 한글로 시작
	let idCheck = /^[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]/
	msg = '아이디는 영문자 또는 한글로 시작해야 합니다.' 
	if( !check(idCheck, id, msg) ) return false
	
	// 비밀번호 체크1
	// - 영문자, 숫자, 특수문자만 사용
	let pwCheck1 = /^[A-Za-z0-9!@#\$%\^\&*\)\(+=._-]+$/
	msg = '비밀번호는  영문자, 숫자, 특수문자만 사용해야 합니다.' 
	if( !check(pwCheck1, pw, msg) ) return false

	// 비밀번호 체크2
	// - 특수 문자 1개 포함
	let pwCheck2 = /(?=.*[!@#\$%\^\&*\)\(+=._-])/
	msg = '비밀번호는 특수문자가 반드시 1개 포함되어야 합니다.' 
	if( !check(pwCheck2, pw, msg) ) return false
	
	// 비밀번호 체크3
	// - 전체 글자수 6글자 이상
	let pwCheck3 = /^.{6,}$/
	msg = '비밀번호는 전체 글자수가 6글자 이상이어야 합니다.' 
	if( !check(pwCheck3, pw, msg) ) return false
	
	// 비밀번호 확인
	// - 비밀번호와 비밀번호 확인값 일치
	msg = '비밀번호와 비밀번호 확인의 값이 일치해야 합니다.' 
	if( !(pwConfirm.value===pw.value) ) {
		pwConfirm.select()
		pwConfirm.focus()
		alert(msg)
		return false
	}
	
	// 이름 체크
	// - 한글만 입력
	let nameCheck = /^[ㄱ-ㅎㅏ-ㅣ가-힣]+$/
	msg = '이름은 한글만 입력해야 합니다.' 
	if( !check(nameCheck, name, msg) ) return false
	
	
	return true
}

// 정규표현식 유효성 검사 함수
function check(regExp, element, msg) {
	
	if( regExp.test(element.value) ) {
		return true
	}
	alert(msg)
	element.select()
	element.focus()
	return false
}