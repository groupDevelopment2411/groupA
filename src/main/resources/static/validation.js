$(document).ready(function() {
    $("#searchForm").submit(function(event) {
        let errorMessages = [];

        let employeeId = $("#employeeId").val();
        let ageFrom = $("#ageFrom").val();
        let ageTo = $("#ageTo").val();
        let startDateFrom = $("#startDateFrom").val();
        let startDateTo = $("#startDateTo").val();
        let endDateFrom = $("#endDateFrom").val();
        let endDateTo = $("#endDateTo").val();

        // 社員ID（数値チェックしてそれ以外はNGをだすうううう）
        if (employeeId && isNaN(employeeId)) {
            errorMessages.push("社員IDは数値のみ許可されています。");
        }

        // 年齢チェック（数値チェックしてそれ以外はNGをだすうううう）
        if (ageFrom && isNaN(ageFrom)) {
            errorMessages.push("年齢（開始）は数値のみ許可されています。");
        }
        if (ageTo && isNaN(ageTo)) {
            errorMessages.push("年齢（終了）は数値のみ許可されています。");
        }

        // 年齢の範囲ェックしてそれ以外はNGをだすうううう
        if (ageFrom && ageTo && parseInt(ageFrom) > parseInt(ageTo)) {
            errorMessages.push("開始年齢は終了年齢以下でなければなりません。");
        }

        // 日付チェックしてそれ以外はNGをだすうううう（yyyy/MM/dd形式）
        let dateRegex = /^\d{4}\/\d{2}\/\d{2}$/;
        if (startDateFrom && !dateRegex.test(startDateFrom)) {
            errorMessages.push("開始日は yyyy/MM/dd の形式で入力してください。");
        }
        if (startDateTo && !dateRegex.test(startDateTo)) {
            errorMessages.push("終了日は yyyy/MM/dd の形式で入力してください。");
        }
        if (endDateFrom && !dateRegex.test(endDateFrom)) {
            errorMessages.push("終了日（開始）は yyyy/MM/dd の形式で入力してください。");
        }
        if (endDateTo && !dateRegex.test(endDateTo)) {
            errorMessages.push("終了日（終了）は yyyy/MM/dd の形式で入力してください。");
        }

        // 終了日の範囲チェックしてそれ以外はNGをだすうううう
        if (endDateFrom && endDateTo && endDateFrom > endDateTo) {
            errorMessages.push("終了日（開始）は終了日（終了）以前にしてください。");
        }

        // エラーがある場合、ポップアップ表示して検索を中止します(ΦωΦ)ﾌﾌﾌ…
        if (errorMessages.length > 0) {
            alert(errorMessages.join("\n"));
            event.preventDefault(); // フォーム送信をキャンセルさせていだきますｗ
        }
    });
});
