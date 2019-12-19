package com.green.testquiz.converter;

import com.green.testquiz.datalayer.entities.Option;
import com.green.testquiz.datalayer.entities.Question;
import com.green.testquiz.enums.QuestionType;
import com.green.testquiz.presentation.OptionDto;
import com.green.testquiz.presentation.QuestionDto;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuestionConverterTest {

    @InjectMocks
    private QuestionConverter questionConverter = new QuestionConverter();

    @Mock
    private OptionConverter optionConverter;

    @Test
    public void toDto() {
        ObjectId objectId = new ObjectId();

        Option option1 = new Option(objectId, "Option1", true, false);
        Option option2 = new Option(objectId, "Option2", true, false);
        Option option3 = new Option(objectId, "Option3", false, true);
        Set<Option> options = new HashSet<>();
        options.add(option1);
        options.add(option2);
        options.add(option3);

        OptionDto optionDto1 = OptionDto.builder()
                .optionId(objectId.toHexString())
                .text("Option1")
                .isChecked(false)
                .build();
        OptionDto optionDto2 = OptionDto.builder()
                .optionId(objectId.toHexString())
                .text("Option2")
                .isChecked(false)
                .build();
        OptionDto optionDto3 = OptionDto.builder()
                .optionId(objectId.toHexString())
                .text("Option3")
                .isChecked(true)
                .build();
        Set<OptionDto> optionDtos = new HashSet<>();
        optionDtos.add(optionDto1);
        optionDtos.add(optionDto2);
        optionDtos.add(optionDto3);

        Question question = new Question(objectId, "Question", QuestionType.ONE_CHOICE, options);
        QuestionDto expected = QuestionDto.builder()
                .questionId(objectId.toHexString())
                .questionType(QuestionType.ONE_CHOICE)
                .text("Question")
                .optionDtos(optionDtos)
                .build();

        when(optionConverter.toDto(option1)).thenReturn(optionDto1);
        when(optionConverter.toDto(option2)).thenReturn(optionDto2);
        when(optionConverter.toDto(option3)).thenReturn(optionDto3);

        QuestionDto actual = questionConverter.toDto(question);

        Assert.assertEquals(expected, actual);
    }
}