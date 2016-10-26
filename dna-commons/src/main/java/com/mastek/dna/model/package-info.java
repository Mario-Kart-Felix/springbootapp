@XmlJavaTypeAdapters({
		@XmlJavaTypeAdapter(type = LocalDate.class, value = LocalDateAdapter.class)
})
package com.mastek.dna.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;

import com.mastek.dna.model.adapter.LocalDateAdapter;
