package com.savek.RestJpaSpringExampleApp.service.enums;

public enum RemoveResult {
	/** Удаление записи прошло успешно */
	OK,
	/** Удаление небыло выполнено, так как имеются ссылки на запись */
	NOT_REMOVED_LINKED_ROWS_FOUND
}
