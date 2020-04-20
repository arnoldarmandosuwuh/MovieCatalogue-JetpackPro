package com.aas.moviecatalogue.util

import androidx.paging.PagedList
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import org.mockito.stubbing.Answer

object PagedListUtil {

    @Suppress("UNCHECKED_CAST")
    fun <T> mockPagedList(list: List<T>): PagedList<T> {
        val pagedList: PagedList<*> = mock(PagedList::class.java)

        val answer: Answer<T> = Answer {
            val index = it.arguments[0] as Int
            list[index]
        }

        `when`<T>(pagedList[ArgumentMatchers.anyInt()] as T).thenAnswer(answer)
        `when`(pagedList.size).thenReturn(list.size)
        return pagedList as PagedList<T>
    }
}