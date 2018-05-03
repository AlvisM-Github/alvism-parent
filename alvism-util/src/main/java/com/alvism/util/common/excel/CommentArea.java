package com.alvism.util.common.excel;

import com.alvism.util.common.excel.command.EachCommand;
import com.alvism.util.common.excel.command.ImageCommand;
import com.alvism.util.common.excel.command.LinkCommand;
import com.alvism.util.common.excel.command.MergeCommand;
import org.jxls.builder.xls.XlsCommentAreaBuilder;

/**
 * Created by JiaMingChen on 2018/4/11.
 */
public class CommentArea {
    static{
        XlsCommentAreaBuilder.addCommandMapping("image", ImageCommand.class);
        XlsCommentAreaBuilder.addCommandMapping("each", EachCommand.class);
        XlsCommentAreaBuilder.addCommandMapping("merge", MergeCommand.class);
        XlsCommentAreaBuilder.addCommandMapping("link", LinkCommand.class);
    }
}
