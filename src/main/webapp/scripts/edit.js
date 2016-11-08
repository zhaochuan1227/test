$(function() {
	listNoteBooksAction();
	$('#save_note').click(saveNoteAction);
	$('#add_note').click(addNote);
	$('#add_notebook').click(addNoteBook);
	$('#rollback_button').click(rollbackBtn);
});

// 定义一个数据模型
var model = {
	noteBooks : [],
	currentNoteBook : [],
	notes : [],
	currentNote : [],
};

// ///////////////////////////** 控制层 **///////////////////////////////////
model.updateNoteBooks = function(noteBooks) {
	this.noteBooks = noteBooks;
	model.updateNoteBooksView();
};
model.updateNotes = function(notes) {
	this.notes = notes;
	model.updateNotesView();
};
model.updatenote = function() {
	model.updateNoteView();
};

// ///////////////////////////** 数据展示层 **///////////////////////////////////
// 展示笔记内容
model.updateNoteView = function() {
	$("#input_note_title").val(this.currentNote.title);
	if (!this.currentNote.body) {
		this.currentNote.body = "";
	}
	um.setContent(this.currentNote.body);
	// console.log(um.getContent());
}
// 展示笔记本列表
model.updateNoteBooksView = function() {
	// console.log(this.noteBooks);
	var view = $("#notebooks-view").empty();
	for (var i = 0; i < this.noteBooks.length; i++) {
		var li = $('<li class="online"></li>');
		var a = $('<a></a>');
		var ico = $('<i class="fa fa-book" title="online" rel="tooltip-bottom"></i> ');
		var button = $('<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">'
				+ '<i class="fa fa-chevron-down"></i></button>');
		var div = $('<div class="note_menu" tabindex="-1">'
				+ '<dl>'
				+ '<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>'
				+ '</dl>' + '</div>');
		li.append(a.append(ico).append(this.noteBooks[i].name).append(button))
				.append(div);
		view.append(li);
		if (this.currentNoteBook.id
				&& this.noteBooks[i].id == this.currentNoteBook.id) {

			a.addClass("checked");
		}
		// 利用JQuery 数据绑定传递数据
		a.data("index", i);
		a.click(function() {
			$("#notebooks-view a").removeClass("checked");
			$(this).addClass("checked");
			var index = $(this).data("index");
			// console.log(index);
			// 获取当前笔记本
			model.currentNoteBook = model.noteBooks[index];
			listNotesAction();
			// console.log(model.currentNoteBook.id);
			return false;
		});
	}
};
// 展示笔记标题列表
model.updateNotesView = function() {
	var view = $("#noteslist").empty();
	// console.log(view[0]);
	// console.log(this.notes);
	for (var i = 0; i < this.notes.length; i++) {
		var li = $('<li class="online"></li>');
		var a = $('<a></a>');
		var i1 = $('<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>');
		var button = $('<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down" id="note_menu_btn">'
				+ '<i class="fa fa-chevron-down"></i></button>');
		var div = $('<div class="note_menu" tabindex="-1">'
				+ '<dl>'
				+ '<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>'
				+ '<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>'
				+ '<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>'
				+ '</dl>' + '</div>');
		a.append(i1).append(this.notes[i].title).append(button);
		view.append(li.append(a).append(div));

		if (model.currentNote && model.notes[i].id == model.currentNote.id) {
			model.currentNote = model.notes[i];
			$("#input_note_title").val(this.currentNote.title);
			a.addClass("checked");
		}
		a.data("index", i);
		// a.data("noteId",this.notes[i].id);
		// 绑定笔记单击事件
		a.click(function() {
			$("#noteslist a").removeClass("checked");
			$(this).addClass("checked");
			var index = $(this).data("index");
			model.currentNote = model.notes[index];
			showNoteAction();
			// console.log(model.currentNote.id);
			return false;
		});
		// 绑定打开笔记菜单事件
		button.click(noteMenuBtn);
		// $("#note_menu_btn,.note_menu").mouseout(noteMenuBtn);
		// 绑定更换笔记本事件
		button.parent().next().find('.btn_move').click(noteMenuMove);
		var delBtn = button.parent().next().find('.btn_delete');
		delBtn.data("noteId", this.notes[i].id);
		// 绑定删除笔记事件
		delBtn.click(function() {
			var url = 'alert/alert_delete_note.jsp';
			$('#can').data("noteId", $(this).data("noteId"));
			$('#can').load(url, function() {
				$('#can .close,#can .cancel').click(function() {
					$('#can').empty();
				});
				$('#can .sure').click(deleteNoteAction);
			});
			return false;
		});
	}

}

// //////////////////////***** 数据请求层*****/////////////////////
// 请求获取笔记本列表
function listNoteBooksAction() {
	var userId = getCookie(USER_ID);
	// get请求语法: $.getJSON(url,[data],[fn])
	$.getJSON('notebook/list.do', {
		'userId' : userId
	}, function(result) {
		if (result.state == SUCCESS) {
			var list = result.data;
			// for(var i=0;i<list.length;i++){ console.log(list[i]); }
			model.updateNoteBooks(list);
		}
	});
};
// 请求笔记列表
function listNotesAction() {
	var url = "note/list.do";
	var data = {
		noteBookId : model.currentNoteBook.id
	};
	$.getJSON(url, data, function(result) {
		if (result.state == SUCCESS) {
			var list = result.data;
			model.updateNotes(list);
		} else {
			alert(result.message);
		}
	});
};
// 请求笔记本内容
function showNoteAction() {
	var url = "note/body.do";
	var data = {
		noteId : model.currentNote.id
	};
	// console.log(data);
	$.getJSON(url, data, function(result) {
		if (result.state == SUCCESS) {
			var note = result.data;
			// console.log(note);
			model.currentNote = note;
			model.updatenote();
		} else {
			alert(result.message);
		}
	});
}
// 增加新的笔记
function addNoteAction() {
	if (!model.currentNoteBook) {
		alert("请先选择笔记本");
		return false;
	}
	var url = "note/add.do";
	var data = {
		noteBookId : model.currentNoteBook.id,
		userId : getCookie(USER_ID),
		title : $("#input_note").val()
	};
	$.post(url, data, function(result) {
		if (result.state == SUCCESS) {
			var note = result.data;
			model.currentNote = note;
			if (model.notes.length) {
				model.notes.unshift({
					id : note.id,
					noteBookId : note.noteBookId,
					title : note.title
				});
			} else {
				model.notes = [ {
					id : note.id,
					noteBookId : note.noteBookId,
					title : note.title
				} ];
			}
			model.updateNotesView();
			model.updatenote(note);
			$('#can').empty();
		} else {
			console.log(result.message);
		}
	});
}

// 保存笔记信息
function saveNoteAction() {
	console.log(model.currentNote);
	if (!model.currentNote) {
		alert("请选择笔记本和笔记");
		return false;
	}
	var noteBookId = model.currentNoteBook.id;
	if (!noteBookId) {
		alert("请选择笔记本");
		return false;
	}
	var id = model.currentNote.id;
	if (!id) {
		alert("请选择笔记");
		return false;
	}
	var title = $("#input_note_title").val();
	var body = um.getContent();
	var userId = getCookie(USER_ID);
	if (!userId) {
		alert("请重新登录！");
		return false;
	}
	var url = "note/update.do";
	var data = {
		"id" : id,
		"noteBookId" : noteBookId,
		"userId" : userId,
		"title" : title,
		"body" : body
	};
	$.post(url, data, function(result) {
		if (result.state == SUCCESS) {
			listNotesAction();

		} else {
			console.log(result.message);
		}
	});
}
// 更改笔记的笔记本
function moveNoteBookAction() {
	// 判断是否需要移动
	var noteBookId = $('#moveSelect').val();
	if (model.currentNoteBook.id == noteBookId) {
		return;
	}
	var url = "note/move.do";
	var noteId = model.currentNote.id;
	if (!noteId) {
		alert("没有当前笔记");
	}
	var data = {
		'id' : noteId,
		'noteBookId' : noteBookId
	};
	$.get(url, data, function(result) {
		console.log(result);
		if (result.data == true) {
			// 处理表现逻辑
			// console.log(model.noteBooks);
			for (var i = 0; i < model.noteBooks.length; i++) {
				if (model.noteBooks[i].id == noteBookId) {
					model.currentNoteBook = model.noteBooks[i];
					model.updateNoteBooksView();
					$('#can').empty();
					break;
				}
			}
			listNotesAction();
		} else {
			$('#can').empty();
			alert("移动失败！");
		}
	});

}
// 增加笔记本
function addNoteBookAction() {
	var userId = getCookie(USER_ID);
	if (!userId) {
		alert("请重新登录!");
	}
	var name = $('#input_notebook').val();
	if (!name) {
		return;
	}
	var data = {
		userId : userId,
		name : name
	};
	var url = "notebook/add.do";
	$.get(url, data, function(result) {
		if (result.state == SUCCESS) {
			model.currentNoteBook = result.data;
			// console.log(model.currentNoteBook);
			// console.log(model.noteBooks);
			model.noteBooks.unshift({
				id : model.currentNoteBook.id,
				createtime : model.currentNoteBook.createtime,
				name : model.currentNoteBook.name,
				typeId : model.currentNoteBook.typeId,
				userId : model.currentNoteBook.userId
			});
			// console.log(model.notes);
			model.updateNoteBooksView();
			model.currentNote = null;
			model.updateNotes({});
			$('#can').empty();
		} else {
			$('#can').empty();
			alert(result.message);
		}
	});
}
//删除笔记
function deleteNoteAction(){
	var noteId = $('#can').data("noteId");
	console.log(noteId);
	
	$('#can').empty();
}
// //////////////////////////** 其他事件 **//////////////////////////
// 笔记名列表的菜单按钮事件
function noteMenuBtn() {
	var menu = $(this).parent().next();
	var i = $(this).children('i');
	menu.toggle(function() {
		i.removeClass('fa-chevron-down').removeClass('fa-chevron-up');
		if ($(this).css("display") == "none") {
			i.addClass('fa-chevron-down');
		} else {
			i.addClass('fa-chevron-up');
		}
	});
	// $(this).parent().next().show(500);
	if (model.currentNote && model.currentNote.id) {
		return false;
	}

}

// 笔记更改笔记本按钮事件
function noteMenuMove() {
	// console.log(1111);
	var url = "alert/alert_move.jsp";
	$('#can').load(url, function() {
		$('#can .close,#can .cancel').click(function() {
			$('#can').empty();
		});
		// $('#can .sure').click(noteMoveAction);
		$('#can h4').append(':  ').append(model.currentNote.title);
		$('#moveSelect').empty();
		// console.log(model.noteBooks);
		for (var i = 0; i < model.noteBooks.length; i++) {
			var name = model.noteBooks[i].name;
			var id = model.noteBooks[i].id;
			var opt = $(" <option>" + name + "</option>");
			opt.val(id);
			if (id == model.currentNoteBook.id) {
				opt.attr("selected", "selected");
			}
			$('#moveSelect').append(opt);
		}
		$('#can .sure').click(moveNoteBookAction);
	});
	return false;
}
// 增加笔记按钮事件
function addNote() {
	var url = 'alert/alert_note.jsp';
	$('#can').load(url, function() {
		$('#can .close,#can .cancel').click(function() {
			$('#can').empty();
		});
		$('#can .sure').click(addNoteAction);
	});
	return false;
}
// 增加笔记本按钮事件
function addNoteBook() {
	var url = 'alert/alert_notebook.jsp';
	$('#can').load(url, function() {
		$('#can .close,#can .cancel').click(function() {
			$('#can').empty();
		});
		$('#can .sure').click(addNoteBookAction);
	});
	return false;
}

// 回收站点击事件
function rollbackBtn() {
	console.log("rollbackBtn");
	return false;
}
