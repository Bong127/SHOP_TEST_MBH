// 삭제 알림창
function deleteProduct(productId,root) {
	const confirmation = confirm("정말 삭제하시겠습니까?");
	if (confirmation) {
		window.location.href =root+"/shop/delete_pro.jsp?productId="+productId;
	}
};