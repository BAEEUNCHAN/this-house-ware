package com.contractor.app.fileroom.service;

import java.util.List;

public interface FileRoomService {
	// 자료실 전체조회
	public List<FileRoomsVO> selectFilerooms(FileRoomsVO fileRoomsVO);

	// 자료실 단건조회
	public FileRoomsVO selectFileroom(FileRoomsVO fileRoomsVO);

	// 자료실별 폴더 전체조회
	public List<FolderVO> selectFolders(Integer fileRoomsNo);

	// 자료실별 파일 전체조회
	public List<FilesVO> selectFiles(Integer fileRoomsNo);

	// 자료실별 폴더 단건조회
	public FolderVO selectFolder(FolderVO folderVO);

	// 자료실별 폴더, 파일 전체조회
	public List<FolderFileVO> selectFolderFile(FolderFileVO folderFileVO);

	// 자료실별 폴더 총 갯수 출력
	public int countFolder(Integer fileRoomsNo);

	// 자료실별 파일 총 갯수 출력
	public int countFile(Integer fileRoomsNo);

	// 파일 업로드
	public int insertFile(FilesVO filesVO);

	// 폴더 삭제
	public int deleteFolder(int folderNo);

	// 파일 삭제
	public int deleteFile(int fileNo);

	// 폴더 추가
	public int insertFolder(FolderVO folderVO);
}
