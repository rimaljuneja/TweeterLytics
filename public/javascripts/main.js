function search() {
alert("test");
}
$(document).ready(function(){
	$("#photos").jqGrid({
		url:'http://jsonplaceholder.typicode.com/posts',
		datatype: "json",
		colNames:['ID', 'User ID', 'Title', 'Body'],
		colModel:[
			{name:'id',index:'id', width:55},
			{name:'userId',index:'userId', width:55},
			{name:'title',index:'title', width:300},	
			{name:'body',index:'body', width:350, sortable:false}		
		],
		rowNum:10,
		loadonce: true,
		rowList:[10,20,30],
		pager: '#pager2',
		sortname: 'id',
		viewrecords: true,
		sortorder: "desc",
		caption:""
	});
});