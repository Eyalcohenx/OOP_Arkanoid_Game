package oop.ass7game;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * class LevelSpecificationReader.
 */
public class LevelSpecificationReader {

    /**
     * @param reader .
     * @return .
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<LevelInformation> levelsInfo = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            line = ((BufferedReader) reader).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (line != null) {
            sb.append(line).append("\n");
            try {
                line = ((BufferedReader) reader).readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //sb now has the file content.
        String[] levels = sb.toString().split("START_LEVEL");
        for (String level : levels) {
            if (level.contains("END_LEVEL")) {
                String[] levelBlocks = level.split("START_BLOCKS");
                String levelInf = levelBlocks[0];
                String blocksInf = levelBlocks[1];
                GeneralLevelInfo currentLevel = levelInfoToLevel(levelInf);
                addBlocks(currentLevel, blocksInf);
                levelsInfo.add(currentLevel);
            }
        }
        return levelsInfo;
    }

    /**
     * @param levelInfo .
     * @return new level based on the level info we got as a string.
     * the way we do it we parse by lines and then every line we check ehat it says
     * and we oprate by it
     */
    private GeneralLevelInfo levelInfoToLevel(String levelInfo) {
        GeneralLevelInfo gli = new GeneralLevelInfo();
        ColorsParser cParser = new ColorsParser();
        //splitting to lines
        String[] lines = levelInfo.split("\n");
        for (String line : lines) {
            if (line.startsWith("background")) {
                String[] sp = line.split(":");
                Background b = new Background();
                if (sp[1].startsWith("color")) {
                    String temp = sp[1].substring(6);
                    b.setBackgroundColor(cParser.colorFromString(temp));
                } else if (sp[1].startsWith("image")) {
                    String path = sp[1].substring(6, sp[1].length() - 1);
                    b.setImagePath(path);
                }
                gli.setBackground(b);
            } else if (line.startsWith("ball_velocities")) {
                String[] split1 = line.split(":");
                String[] split2 = split1[1].split(" ");
                ArrayList velocities = new ArrayList();
                for (String velDef : split2) {
                    String[] velDefs = velDef.split(",");
                    velocities.add(Velocity.fromAngleAndSpeed(Integer.parseInt(velDefs[0]),
                            Integer.parseInt(velDefs[1])));
                }
                gli.setBallVelocities(velocities);

            } else if (line.startsWith("level_name")) {
                String[] split1 = line.split(":");
                gli.setLevelName(split1[1]);

            } else if (line.startsWith("paddle_speed")) {
                String[] split1 = line.split(":");
                gli.setPaddleSpeed(Integer.parseInt(split1[1]));

            } else if (line.startsWith("paddle_width")) {
                String[] split1 = line.split(":");
                gli.setPaddleWidth(Integer.parseInt(split1[1]));

            } else if (line.startsWith("block_definitions")) {
                String[] split1 = line.split(":");
                gli.setBlocksFactory(new BlocksFromSymbolsFactory(split1[1]));

            } else if (line.startsWith("blocks_start_x")) {
                String[] split1 = line.split(":");
                gli.setBlocksStartX(Integer.parseInt(split1[1]));

            } else if (line.startsWith("blocks_start_y")) {
                String[] split1 = line.split(":");
                gli.setBlocksStartY(Integer.parseInt(split1[1]));

            } else if (line.startsWith("row_height")) {
                String[] split1 = line.split(":");
                gli.setRowHeight(Integer.parseInt(split1[1]));

            } else if (line.startsWith("num_blocks")) {
                String[] split1 = line.split(":");
                gli.setNumBlocks(Integer.parseInt(split1[1]));

            }
        }

        return gli;
    }

    /**
     * @param levelInfo  .
     * @param blocksInfo .
     */
    private void addBlocks(GeneralLevelInfo levelInfo, String blocksInfo) {
        //adding blocks with the block factory
        int blocksStartX = levelInfo.getBlocksStartX();
        int blocksStartY = levelInfo.getBlocksStartY();
        int rowHeight = levelInfo.getRowHeight();
        BlocksFromSymbolsFactory bFactory = levelInfo.getBlocksFactory();
        List<Block> blocksList = new ArrayList<>();
        String[] lines = blocksInfo.split("\n");
        for (String line : lines) {
            if (line.equals("END_BLOCKS")) {
                break;
            }
            int startXforLine = blocksStartX;
            for (int j = 0, n = line.length(); j < n; j++) {
                String c = line.charAt(j) + "";
                if (bFactory.isBlockSymbol(c)) {
                    //if its block.
                    Block temp = bFactory.getBlock(c, startXforLine, blocksStartY);
                    blocksList.add(temp);
                    startXforLine += temp.getWidth();
                } else if (bFactory.isSpaceSymbol(c)) {
                    //if its space symbol.
                    startXforLine += bFactory.getSpaceWidth(c);
                } else {
                    //else its not supported symbol.
                    try {
                        throw new Exception("unknown Symbol");
                    } catch (Exception e) {
                        System.out.println("unk char: " + c);
                    }
                }
            }
            blocksStartY += rowHeight;
        }
        levelInfo.setBlockDefinitions(blocksList);
    }
}